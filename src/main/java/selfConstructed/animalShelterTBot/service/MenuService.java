package selfConstructed.animalShelterTBot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import selfConstructed.animalShelterTBot.keyboard.Keyboard;
import selfConstructed.animalShelterTBot.service.messageHandler.MsgHandler;

@Service
@AllArgsConstructor
public class MenuService {
    private final Logger logger = LoggerFactory.getLogger(MsgHandler.class);
    private final TelegramBot telegramBot;
    private final Keyboard keyboard;
    private final WelcomeHandler welcomeHandler;

    /**
     * Sends a message with the choice of shelter to the user.
     *
     * @param chatId user's chat identifier
     */

    public void chooseShelter(long chatId) {
        String message = "Выбери нужный приют \uD83D\uDC47";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getShelter();
        EditMessageText editMessageText = new EditMessageText(chatId, welcomeHandler.getMessageId(), message)
                .replyMarkup(inlineKeyboardMarkup);
        telegramBot.execute(editMessageText);
        logger.info("Отправлено сообщение с выбором приюта в чат {}: {}", chatId, message);
    }

    /**
     * Sends a menu for selecting options related to shelter dogs to the specified chat ID.
     *
     * @param chatId The ID of the chat where the menu should be sent.
     */
    public void getShelterMenuDogs(long chatId) {
        String message = "В следующем меню выберите интересующий вас пункт";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getMenuAboutShelterDogs();
        EditMessageText editMessageText = new EditMessageText(chatId, welcomeHandler.getMessageId(), message)
                .replyMarkup(inlineKeyboardMarkup);
        telegramBot.execute(editMessageText);
        logger.info("Отправлено сообщение с выбором меню в чат {}: {}", chatId, message);
    }

    /**
     * Sends a menu for selecting options related to shelter cats to the specified chat ID.
     *
     * @param chatId The ID of the chat where the menu should be sent.
     */
    public void getShelterMenuCats(long chatId) {
        String message = "В следующем меню выберите интересующий вас пункт";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getMenuAboutShelterCats();
        EditMessageText editMessageText = new EditMessageText(chatId, welcomeHandler.getMessageId(), message)
                .replyMarkup(inlineKeyboardMarkup);
        telegramBot.execute(editMessageText);
        logger.info("Отправлено сообщение с выбором меню в чат {}: {}", chatId, message);
    }

    //этот метод нужно будет удалить когда все приложение будет работать
    public void sendMock(long chatId) {
        String message = "\uD83D\uDED1Ведутся ремонтные работы\uD83D\uDED1";
        telegramBot.execute(new SendMessage(chatId, message));
    }
}
