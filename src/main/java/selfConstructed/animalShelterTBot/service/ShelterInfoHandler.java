package selfConstructed.animalShelterTBot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import selfConstructed.animalShelterTBot.keyboard.Keyboard;
import selfConstructed.animalShelterTBot.model.Shelter;
import selfConstructed.animalShelterTBot.model.TypePet;
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
    private final ShelterRepository repository;
    private final Keyboard keyboard;
    @Getter
    private Integer messageId;

    /**
     * Constructs a ShelterInfoHandler with the specified TelegramBot and ShelterRepository.
     *
     * @param telegramBot The TelegramBot instance used to send messages.
     * @param repository  The repository for accessing shelter information.
     * @param keyboard    The keyboard for telegram bot
     */
    public ShelterInfoHandler(TelegramBot telegramBot, ShelterRepository repository, Keyboard keyboard) {
        this.telegramBot = telegramBot;
        this.repository = repository;
        this.keyboard = keyboard;
    }

    /**
     * Sends information about dog shelters to the specified chat ID.
     *
     * @param chatId The ID of the chat where the information should be sent.
     */
    public void shelterDogInfo(long chatId) {
        Optional<Shelter> dogShelter = repository.findByDogs(TypePet.Dog.name());
        getInfoAboutShelterDogs(chatId, dogShelter);
    }

    /**
     * Sends information about cat shelters to the specified chat ID.
     *
     * @param chatId The ID of the chat where the information should be sent.
     */
    public void shelterCatInfo(long chatId) {
        Optional<Shelter> catShelter = repository.findByCats(TypePet.Cat.name());
        getInfoAboutShelterCats(chatId, catShelter);
    }

    private void getInfoAboutShelterDogs(long chatId, Optional<Shelter> animalShelter) {
        if (animalShelter.isPresent()) {
            Shelter shelter = animalShelter.get();
            String message = "Адрес: " + shelter.getAddress() + "\n" +
                    "Контакты: " + shelter.getContactInfo() + "\n" +
                    "Название: " + shelter.getNameOfTheShelter() + "\n" +
                    "Время работы: " + shelter.getOpeningHours();
            InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getGoBackButtonDogs();
            SendMessage sendMessage = new SendMessage(chatId, message).replyMarkup(inlineKeyboardMarkup);
            SendResponse sendResponse = telegramBot.execute(sendMessage);
            if (sendResponse.isOk()) {
                messageId = sendResponse.message().messageId();
            }
            logger.info("Отправлено сообщение в чат: {}, {}", chatId, message);
        } else {
            telegramBot.execute(new SendMessage(chatId, "Нет подходящих приютов"));
            logger.info("Отправлено сообщение в чат: {}, Нет подходящих приютов", chatId);
        }
    }

    private void getInfoAboutShelterCats(long chatId, Optional<Shelter> animalShelter) {
        if (animalShelter.isPresent()) {
            Shelter shelter = animalShelter.get();
            String message = "Адрес: " + shelter.getAddress() + "\n" +
                    "Контакты: " + shelter.getContactInfo() + "\n" +
                    "Название: " + shelter.getNameOfTheShelter() + "\n" +
                    "Время работы: " + shelter.getOpeningHours();
            InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getGoBackButtonCats();
            SendMessage sendMessage = new SendMessage(chatId, message).replyMarkup(inlineKeyboardMarkup);
            SendResponse sendResponse = telegramBot.execute(sendMessage);
            if (sendResponse.isOk()) {
                messageId = sendResponse.message().messageId();
            }
            logger.info("Отправлено сообщение в чат: {}, {}", chatId, message);
        } else {
            telegramBot.execute(new SendMessage(chatId, "Нет подходящих приютов"));
            logger.info("Отправлено сообщение в чат: {}, Нет подходящих приютов", chatId);
        }
    }
}