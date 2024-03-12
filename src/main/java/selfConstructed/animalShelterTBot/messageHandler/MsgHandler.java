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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class MsgHandler {

    private final Logger logger = LoggerFactory.getLogger(MsgHandler.class);
    private final TelegramBot telegramBot;
    private final Keyboard keyboard;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final boolean buttonEnabled = true;

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
        }
    }

    /**
     * processing callback from user
     */
    public void handleCallBack(CallbackQuery callbackQuery) {
        Long chatId = callbackQuery.from().id();
        String text = callbackQuery.data();
        if (!buttonEnabled) {
            return;
        }
        if ("Начнем работу".equals(text)) {
            processButton(chatId, text);
            chooseShelter(chatId);
            disableButtonsTemporarily();
            return;
        }
        if ("Коты".equals(text)) {
            processButton(chatId, text);
            getShelterMenu(chatId);
            disableButtonsTemporarily();
            return;
        }
        if ("Собаки".equals(text)) {
            processButton(chatId, text);
            getShelterMenu(chatId);
            disableButtonsTemporarily();
            return;
        }
        if ("Информация".equals(text)) {
            processButton(chatId, text);
            sendMock(chatId);
            return;
        }
        if ("Как взять".equals(text)) {
            processButton(chatId, text);
            sendMock(chatId);
            return;
        }
        if ("Отчет".equals(text)) {
            processButton(chatId, text);
            sendMock(chatId);
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
        String message = "Выбери нужный приют \uD83D\uDC47";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getShelter();
        telegramBot.execute(new SendMessage(chatId, message).replyMarkup(inlineKeyboardMarkup));
        logger.info("Отправлено сообщение с выбором приюта в чат {}: {}", chatId, message);
    }

    private void getShelterMenu(long chatId) {
        String message = "Ознакомьтесь с меню и выберите нужный пункт";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getMenuAboutShelter();
        telegramBot.execute(new SendMessage(chatId, message).replyMarkup(inlineKeyboardMarkup));
        logger.info("Отправлено сообщение с выбором меню в чат {}: {}", chatId, message);
    }

    private void sendMock(long chatId) {
        String message = "\uD83D\uDED1Ведутся ремонтные работы\uD83D\uDED1";
        telegramBot.execute(new SendMessage(chatId, message));
    }

    private void processButton(long chatId, String callBack) {
        String message = "Вы нажали на кнопку " + callBack;
        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Пользователь {} нажал на кнопку {}", chatId, callBack);
    }

    private void disableButtonsTemporarily() {
        AtomicBoolean buttonEnabled = new AtomicBoolean(false);
        scheduler.schedule(() -> buttonEnabled.set(true), 30, TimeUnit.SECONDS); // Enable buttons after 30 seconds
    }
}
