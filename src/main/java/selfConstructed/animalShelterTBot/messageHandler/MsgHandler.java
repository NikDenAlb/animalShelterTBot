package selfConstructed.animalShelterTBot.messageHandler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import selfConstructed.animalShelterTBot.keyboard.Keyboard;

@Service
public class MsgHandler {

    private final Logger logger = LoggerFactory.getLogger(MsgHandler.class);
    private final TelegramBot telegramBot;
    private final Keyboard keyboard;

    public MsgHandler(TelegramBot telegramBot, Keyboard keyboard) {
        this.telegramBot = telegramBot;
        this.keyboard = keyboard;
    }

    /**
     * processing message from user
     */
    public void handleMessage(Message message) {
        Long chatId = message.chat().id();
        String text = message.text();
        if ("/start".equals(text)) {
            sendWelcomeMessage(chatId);
            return;
        }
    }

    /**
     * processing callback from user
     */
    public void handleCallBack(CallbackQuery callbackQuery) {
        Long chatId = callbackQuery.from().id();
        String text = callbackQuery.data();
        if ("callback".equals(text)) {
            chooseShelter(chatId);
            return;
        }
        if ("киски".equals(text)) {
            sendMock(chatId);
            return;
        }
        if ("песики".equals(text)) {
            sendMock(chatId);
            return;
        }
    }

    /**
     * welcome message
     * added inline keyboard
     * sending welcome message to user with test button
     */
    private void sendWelcomeMessage(long chatId) {
        String welcomeMessage = "Добро пожаловать! Я бот."
                + '\n' + "Для начала работы нажми кнопку КНОПКУ";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getTestInlineButton();
        telegramBot.execute(new SendMessage(chatId, welcomeMessage).replyMarkup(inlineKeyboardMarkup));
        logger.info("Отправлено приветственное сообщение в чат {}: {}", chatId, welcomeMessage);
    }

    private void chooseShelter(long chatId) {
        String message = "Выбери нужый приют \uD83D\uDC47";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getShelter();
        telegramBot.execute(new SendMessage(chatId, message).replyMarkup(inlineKeyboardMarkup));
        logger.info("Отправлено приветственное сообщение в чат {}: {}", chatId, message);
    }

    private void sendMock(long chatId) {
        String message = "\uD83D\uDED1Ведутся ремонтные работы\uD83D\uDED1";
        telegramBot.execute(new SendMessage(chatId, message));
    }
}
