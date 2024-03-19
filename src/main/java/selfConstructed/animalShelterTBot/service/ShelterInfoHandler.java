package selfConstructed.animalShelterTBot.service;

import org.springframework.stereotype.Service;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selfConstructed.animalShelterTBot.model.cat.ShelterCat;
import selfConstructed.animalShelterTBot.repository.SheltersRepository;

import java.util.Optional;

@Service
public class ShelterInfoHandler {
    private final Logger logger = LoggerFactory.getLogger(ShelterInfoHandler.class);
    private final TelegramBot telegramBot;
    private SheltersRepository repository;

    public ShelterInfoHandler(TelegramBot telegramBot, SheltersRepository repository) {
        this.telegramBot = telegramBot;
        this.repository = repository;
    }

    public void shelterDogInfo(long chatId) {
        Optional<SheltersRepository> dog = repository.findByDogs();
        if (dog.isPresent()) {
            Shelters shelter = dog.get();
            String message = "Адрес : " + shelter.getAddress() +
                    "\nКонтактная информация : " + shelter.getContactInfo() +
                    "\nНаименование : " + shelter.getNameOfTheShelter() +
                    "\nВремя работы: " + shelter.getOpeningHours();
            telegramBot.execute(new SendMessage(chatId, message));
        } else {
            telegramBot.execute(new SendMessage(chatId, "Нет подходящих приютов"));
        }
    }

    public void shelterCatInfo(long chatId) {
        Optional<SheltersRepository> cat = repository.findByCats();
        if (cat.isPresent()) {
            Shelters shelterCat = cat.get();
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
