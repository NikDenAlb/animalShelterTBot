package selfConstructed.animalShelterTBot.service.messageHandler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageReplyMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import selfConstructed.animalShelterTBot.keyboard.Keyboard;
import selfConstructed.animalShelterTBot.service.ShelterInfoHandler;
import selfConstructed.animalShelterTBot.service.ShelterInfoSender;
import selfConstructed.animalShelterTBot.service.WelcomeHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Handler for messages from the user and callbacks.
 *
 * @author shinkevich
 */
@Service
public class MsgHandler {

    private final Logger logger = LoggerFactory.getLogger(MsgHandler.class);
    private final TelegramBot telegramBot;
    private final ShelterInfoHandler shelterInfoHandler;
    private final WelcomeHandler welcomeHandler;
    private final ShelterInfoSender shelterInfoSender;
    private final Keyboard keyboard;
    private final Map<Long, Integer> previousMessages = new HashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * Constructor for MsgHandler.
     *
     * @param telegramBot        TelegramBot object
     * @param shelterInfoHandler ShelterInfoHandler object
     * @param welcomeHandler     WelcomeHandler object
     * @param shelterInfoSender
     * @param keyboard           Keyboard object
     */
    public MsgHandler(TelegramBot telegramBot, ShelterInfoHandler shelterInfoHandler,
                      WelcomeHandler welcomeHandler, ShelterInfoSender shelterInfoSender, Keyboard keyboard) {
        this.telegramBot = telegramBot;
        this.shelterInfoHandler = shelterInfoHandler;
        this.welcomeHandler = welcomeHandler;
        this.shelterInfoSender = shelterInfoSender;
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
            welcomeHandler.sendWelcomeMessage(chatId);
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
        int messageId = callbackQuery.message().messageId();
        previousMessages.put(chatId, messageId);
        boolean buttonEnabled = true;
        if (!buttonEnabled) {
            return;
        }
        switch (text) {
            case "Коты" -> {
                disableButtonsTemporarily();
                getShelterMenuCats(chatId);
            }
            case "Собаки" -> {
                disableButtonsTemporarily();
                getShelterMenuDogs(chatId);
            }
            case "Информация о приюте для собак" -> {
                disableButtonsTemporarily();
                shelterInfoHandler.shelterDogInfo(chatId);
            }
            case "Информация о приюте для котов" -> {
                disableButtonsTemporarily();
                shelterInfoHandler.shelterCatInfo(chatId);
            }
            case "Как взять собаку" -> {
                disableButtonsTemporarily();
                shelterInfoSender.sendAdoptionInfo(chatId);
                getShelterMenuDogs(chatId);
            }
            case "Как взять кота" -> {
                disableButtonsTemporarily();
                shelterInfoSender.sendAdoptionInfo(chatId);
                getShelterMenuCats(chatId);
            }
            case "Назад", "Отчет о собаке", "Отчет о коте" -> {
                disableButtonsTemporarily();
                sendMock(chatId);
//                Integer previousMessageId = previousMessages.get(chatId);
//                if (previousMessageId != null) {
//                    EditMessageText editMessageText = new EditMessageText(chatId, previousMessageId, "Вернулись назад.");
//
//                    telegramBot.execute(editMessageText);
//                    logger.info("Отправлено предыдущее сообщение в чат {}: {}", chatId, previousMessageId);
//                } else {
//                    telegramBot.execute(new SendMessage(chatId, "Нет предыдущего сообщения."));
//                }
            }
            default -> {
                disableButtonsTemporarily();
                chooseShelter(chatId);
            }
        }
    }


    /**
     * Sends a message with the choice of shelter to the user.
     *
     * @param chatId user's chat identifier
     */
    private void chooseShelter(long chatId) {
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
    private void getShelterMenuDogs(long chatId) {
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
    private void getShelterMenuCats(long chatId) {
        String message = "В следующем меню выберите интересующий вас пункт";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getMenuAboutShelterCats();
        EditMessageText editMessageText = new EditMessageText(chatId, welcomeHandler.getMessageId(), message)
                .replyMarkup(inlineKeyboardMarkup);
        telegramBot.execute(editMessageText);
        logger.info("Отправлено сообщение с выбором меню в чат {}: {}", chatId, message);
    }

    //этот метод нужно будет удалить когда все приложение будет работать
    private void sendMock(long chatId) {
        String message = "\uD83D\uDED1Ведутся ремонтные работы\uD83D\uDED1";
        telegramBot.execute(new SendMessage(chatId, message));
    }

    /**
     * Temporarily disables buttons for a specified time.
     */
    private void disableButtonsTemporarily() {
        AtomicBoolean buttonEnabled = new AtomicBoolean(false);
        scheduler.schedule(() -> buttonEnabled.set(true), 30, TimeUnit.SECONDS);
    }
}