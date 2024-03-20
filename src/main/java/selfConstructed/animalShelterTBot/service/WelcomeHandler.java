package selfConstructed.animalShelterTBot.service;

import org.springframework.stereotype.Service;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selfConstructed.animalShelterTBot.keyboard.Keyboard;

/**
 * @author shinkevich
 */
@Service
public class WelcomeHandler {
    private final Logger logger = LoggerFactory.getLogger(WelcomeHandler.class);
    private final TelegramBot telegramBot;
    private final Keyboard keyboard;

    public WelcomeHandler(TelegramBot telegramBot, Keyboard keyboard) {
        this.telegramBot = telegramBot;
        this.keyboard = keyboard;
    }

    /**
     * Sends a welcome message to the user with a built-in keyboard.
     *
     * @param chatId user's chat identifier
     */
    public void sendWelcomeMessage(long chatId) {
        String welcomeMessage = "Добро пожаловать! Я бот."
                + '\n' + "Для начала работы нажми кнопку КНОПКУ";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getStartInlineButton();
        telegramBot.execute(new SendMessage(chatId, welcomeMessage).replyMarkup(inlineKeyboardMarkup));
        logger.info("Отправлено приветственное сообщение в чат {}: {}", chatId, welcomeMessage);
    }
}
