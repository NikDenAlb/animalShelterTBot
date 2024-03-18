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
import selfConstructed.animalShelterTBot.model.dog.ShelterDog;
import selfConstructed.animalShelterTBot.repository.ShelterCatRepo;
import selfConstructed.animalShelterTBot.repository.ShelterDogRepo;

import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Handler for messages from the user and callbacks.
 */
@Service
public class MsgHandler {

    private final Logger logger = LoggerFactory.getLogger(MsgHandler.class);
    private final TelegramBot telegramBot;
    private final ShelterDogRepo dogRepo;
    private final ShelterCatRepo catRepo;
    private final Keyboard keyboard;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final boolean buttonEnabled = true;

    /**
     * Constructor for MsgHandler.
     *
     * @param telegramBot TelegramBot object
     * @param dogRepo
     * @param catRepo
     * @param keyboard    Keyboard object
     */
    public MsgHandler(TelegramBot telegramBot, ShelterDogRepo dogRepo, ShelterCatRepo catRepo, Keyboard keyboard) {
        this.telegramBot = telegramBot;
        this.dogRepo = dogRepo;
        this.catRepo = catRepo;
        this.keyboard = keyboard;
    }

    /**
     * Processing a message from the user.
     *
     * @param message Message object
     */
    public void handleMessage(Message message) {
        Long chatId = message.chat().id();
        String text = message.text();
        if ("/start".equals(text)) {
            sendWelcomeMessage(chatId);
        }
    }

    /**
     * Processing a callback from the user.
     *
     * @param callbackQuery object CallbackQuery
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
            getShelterMenuCats(chatId);
            disableButtonsTemporarily();
            return;
        }
        if ("Собаки".equals(text)) {
            processButton(chatId, text);
            getShelterMenuDogs(chatId);
            disableButtonsTemporarily();
            return;
        }
        if ("Информация собак".equals(text)) {
            //processButton(chatId, text);
            disableButtonsTemporarily();
            shelterDogInfo(chatId);//даем инфо по приюту
            return;//нужно добавить кнопу возврата в предыдущее меню
        }
        if ("Как взять".equals(text)) {
            disableButtonsTemporarily();
            processButton(chatId, text);
            sendMock(chatId);
            return;
        }
        if ("Отчет".equals(text)) {
            disableButtonsTemporarily();
            processButton(chatId, text);
            sendMock(chatId);
        }
    }

    /**
     * Sends a welcome message to the user with a built-in keyboard.
     *
     * @param chatId user's chat identifier
     */
    private void sendWelcomeMessage(long chatId) {
        String welcomeMessage = "Добро пожаловать! Я бот."
                + '\n' + "Для начала работы нажми кнопку КНОПКУ";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getTestInlineButton();
        telegramBot.execute(new SendMessage(chatId, welcomeMessage).replyMarkup(inlineKeyboardMarkup));
        logger.info("Отправлено приветственное сообщение в чат {}: {}", chatId, welcomeMessage);
    }

    /**
     * Sends a message with the choice of shelter to the user.
     *
     * @param chatId user's chat identifier
     */
    private void chooseShelter(long chatId) {
        String message = "Выбери нужный приют \uD83D\uDC47";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getShelter();
        telegramBot.execute(new SendMessage(chatId, message).replyMarkup(inlineKeyboardMarkup));
        logger.info("Отправлено сообщение с выбором приюта в чат {}: {}", chatId, message);
    }

    /**
     * Sends a message with the shelter menu to the user.
     *
     * @param chatId user's chat identifier
     */
    private void getShelterMenuDogs(long chatId) {
        String message = "Ознакомьтесь с меню и выберите нужный пункт";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getMenuAboutShelterDogs();
        telegramBot.execute(new SendMessage(chatId, message).replyMarkup(inlineKeyboardMarkup));
        logger.info("Отправлено сообщение с выбором меню в чат {}: {}", chatId, message);
    }

    private void getShelterMenuCats(long chatId) {
        String message = "Ознакомьтесь с меню и выберите нужный пункт";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getMenuAboutShelterCats();
        telegramBot.execute(new SendMessage(chatId, message).replyMarkup(inlineKeyboardMarkup));
        logger.info("Отправлено сообщение с выбором меню в чат {}: {}", chatId, message);
    }

    private void sendMock(long chatId) {
        String message = "\uD83D\uDED1Ведутся ремонтные работы\uD83D\uDED1";
        telegramBot.execute(new SendMessage(chatId, message));
    }

    /**
     * Handles a button click by the user.
     *
     * @param chatId   user's chat identifier
     * @param callBack callback text
     */
    private void processButton(long chatId, String callBack) {
        String message = "Вы нажали на кнопку " + callBack;
        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Пользователь {} нажал на кнопку {}", chatId, callBack);
    }

    /**
     * Temporarily disables buttons for a specified time.
     */
    private void disableButtonsTemporarily() {
        AtomicBoolean buttonEnabled = new AtomicBoolean(false);
        scheduler.schedule(() -> buttonEnabled.set(true), 30, TimeUnit.SECONDS);
    }

    private void shelterDogInfo(long chatId) {
        Optional<ShelterDog> dog = dogRepo.findById("1");
        if (dog.isPresent()) {
            ShelterDog shelterDog = dog.get();
            String message = "Адрес : " + shelterDog.getAddress() +
                    "\nКонтактная информация : " + shelterDog.getContactInfo() +
                    "\nНаименование : " + shelterDog.getNameOfTheShelter() +
                    "\nВремя работы: " + shelterDog.getOpeningHours();
            telegramBot.execute(new SendMessage(chatId, message));
        } else {
            telegramBot.execute(new SendMessage(chatId, "Нет подходящих приютов"));
        }
    }
}
