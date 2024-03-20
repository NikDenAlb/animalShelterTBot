package selfConstructed.animalShelterTBot.service;

import org.springframework.stereotype.Service;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selfConstructed.animalShelterTBot.model.Shelter;
import selfConstructed.animalShelterTBot.repository.ShelterRepository;

import java.util.Optional;

/**
 * Handles requests related to shelter information for dogs and cats.
 *
 * @author shinkevich
 */
@Service
public class ShelterInfoHandler {
    private final Logger logger = LoggerFactory.getLogger(ShelterInfoHandler.class);
    private final TelegramBot telegramBot;
    private ShelterRepository repository;

    /**
     * Constructs a ShelterInfoHandler with the specified TelegramBot and ShelterRepository.
     *
     * @param telegramBot The TelegramBot instance used to send messages.
     * @param repository  The repository for accessing shelter information.
     */
    public ShelterInfoHandler(TelegramBot telegramBot, ShelterRepository repository) {
        this.telegramBot = telegramBot;
        this.repository = repository;
    }

    /**
     * Sends information about dog shelters to the specified chat ID.
     *
     * @param chatId The ID of the chat where the information should be sent.
     */
    public void shelterDogInfo(long chatId) {
        Optional<Shelter> dogShelter = repository.findByDogs();
        if (dogShelter.isPresent()) {
            Shelter shelter = dogShelter.get();
            String message = "Адрес : " + shelter.getAddress() +
                    "\nКонтактная информация : " + shelter.getContactInfo() +
                    "\nНаименование : " + shelter.getNameOfTheShelter() +
                    "\nВремя работы: " + shelter.getOpeningHours();
            telegramBot.execute(new SendMessage(chatId, message));
        } else {
            telegramBot.execute(new SendMessage(chatId, "Нет подходящих приютов"));
        }
    }

    /**
     * Sends information about cat shelters to the specified chat ID.
     *
     * @param chatId The ID of the chat where the information should be sent.
     */
    public void shelterCatInfo(long chatId) {
        Optional<Shelter> catShelter = repository.findByCats();
        if (catShelter.isPresent()) {
            Shelter shelterCat = catShelter.get();
            String message = "Адрес : " + shelterCat.getAddress() +
                    "\nКонтактная информация : " + shelterCat.getContactInfo() +
                    "\nНаименование : " + shelterCat.getNameOfTheShelter() +
                    "\nВремя работы: " + shelterCat.getOpeningHours();
            telegramBot.execute(new SendMessage(chatId, message));
        } else {
            telegramBot.execute(new SendMessage(chatId, "Нет подходящих приютов"));
        }
    }
}
