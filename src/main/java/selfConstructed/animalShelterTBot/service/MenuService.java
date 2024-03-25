package selfConstructed.animalShelterTBot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import selfConstructed.animalShelterTBot.keyboard.Keyboard;
import selfConstructed.animalShelterTBot.service.messageHandler.MsgHandler;

/**
 * @author shinkevich
 */
@Service

public class MenuService {
    private final Logger logger = LoggerFactory.getLogger(MsgHandler.class);
    private final TelegramBot telegramBot;
    private final Keyboard keyboard;
    private final WelcomeHandler welcomeHandler;
    @Getter
    private Integer messageId;

    public MenuService(TelegramBot telegramBot, Keyboard keyboard, WelcomeHandler welcomeHandler) {
        this.telegramBot = telegramBot;
        this.keyboard = keyboard;
        this.welcomeHandler = welcomeHandler;
    }

    /**
     * Sends a message with the choice of shelter to the user.
     *
     * @param chatId user's chat identifier
     */

    public void chooseShelterReWrite(long chatId) {
        String message = "Выбери нужный приют \uD83D\uDC47";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getShelter();
        processEditMessageSendText(chatId, message, inlineKeyboardMarkup);
        logger.info("Отправлено сообщение с выбором приюта в чат {}: {}", chatId, message);
    }

    public void chooseShelterNew(long chatId) {
        String message = "Выбери нужный приют \uD83D\uDC47";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getShelter();
        processSendMessage(chatId, message, inlineKeyboardMarkup);
        logger.info("Отправлено сообщение с выбором приюта в чат {}: {}", chatId, message);
    }

    /**
     * Sends a menu for selecting options related to shelter dogs to the specified chat ID.
     *
     * @param chatId The ID of the chat where the menu should be sent.
     */
    public void getShelterMenuDogsReWrite(long chatId) {
        String message = " \uD83D\uDC15 Выберите интересующий вас пункт \uD83D\uDC15";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getMenuAboutShelterDogs();
        processEditMessageSendText(chatId, message, inlineKeyboardMarkup);
        logger.info("Отправлено сообщение с выбором меню в чат {}: {}", chatId, message);
    }

    public void getShelterMenuDogsNew(long chatId) {
        String message = "\uD83D\uDC15 Выберите интересующий вас пункт \uD83D\uDC15";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getMenuAboutShelterDogs();
        processSendMessage(chatId, message, inlineKeyboardMarkup);
        logger.info("Отправлено сообщение с выбором меню в чат {}: {}", chatId, message);
    }

    /**
     * Sends a menu for selecting options related to shelter cats to the specified chat ID.
     *
     * @param chatId The ID of the chat where the menu should be sent.
     */
    public void getShelterMenuCatsReWrite(long chatId) {
        String message = "\uD83D\uDC08 Выберите интересующий вас пункт \uD83D\uDC08";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getMenuAboutShelterCats();
        processEditMessageSendText(chatId, message, inlineKeyboardMarkup);
        logger.info("Отправлено сообщение с выбором меню в чат {}: {}", chatId, message);
    }

    public void getShelterMenuCatsNew(long chatId) {
        String message = "\uD83D\uDC08 Выберите интересующий вас пункт \uD83D\uDC08";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getMenuAboutShelterCats();
        processSendMessage(chatId, message, inlineKeyboardMarkup);
        logger.info("Отправлено сообщение с выбором меню в чат {}: {}", chatId, message);
    }

    //этот метод нужно будет удалить когда все приложение будет работать
    public void sendMock(long chatId) {
        String message = "\uD83D\uDED1Ведутся ремонтные работы\uD83D\uDED1";
        SendResponse sendResponse = telegramBot.execute(new SendMessage(chatId, message));
        if (sendResponse.isOk()) {
            messageId = sendResponse.message().messageId();
        }
    }

    private void processSendMessage(long chatId, String message, InlineKeyboardMarkup inlineKeyboardMarkup) {
        SendMessage sendMessage = new SendMessage(chatId, message).replyMarkup(inlineKeyboardMarkup);
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (sendResponse.isOk()) {
            messageId = sendResponse.message().messageId();
        }
    }

    private void processEditMessageSendText(long chatId, String message, InlineKeyboardMarkup inlineKeyboardMarkup) {
        EditMessageText editMessageText = new EditMessageText(chatId, welcomeHandler.getMessageId(), message)
                .replyMarkup(inlineKeyboardMarkup);
        telegramBot.execute(editMessageText);
    }
}
