package selfConstructed.animalShelterTBot.service.messageHandler;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import selfConstructed.animalShelterTBot.service.MenuService;
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
@AllArgsConstructor
public class MsgHandler {

    private final Logger logger = LoggerFactory.getLogger(MsgHandler.class);
    private final MenuService menu;
    private final ShelterInfoHandler shelterInfoHandler;
    private final WelcomeHandler welcomeHandler;
    private final ShelterInfoSender shelterInfoSender;
    private final Map<Long, Integer> previousMessages = new HashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

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
                menu.getShelterMenuCatsReWrite(chatId);
            }
            case "Собаки" -> {
                disableButtonsTemporarily();
                menu.getShelterMenuDogsReWrite(chatId);
            }
            case "Информация о приюте для собак" -> {
                disableButtonsTemporarily();
                shelterInfoHandler.shelterDogInfo(chatId);
                menu.getShelterMenuDogsNew(chatId);
            }
            case "Информация о приюте для котов" -> {
                disableButtonsTemporarily();
                shelterInfoHandler.shelterCatInfo(chatId);
                menu.getShelterMenuCatsNew(chatId);
            }
            case "Как взять собаку" -> {
                disableButtonsTemporarily();
                shelterInfoSender.sendAdoptionInfo(chatId);
                menu.getShelterMenuDogsNew(chatId);
            }
            case "Как взять кота" -> {
                disableButtonsTemporarily();
                shelterInfoSender.sendAdoptionInfo(chatId);
                menu.getShelterMenuCatsNew(chatId);
            }
            case "Назад", "Отчет о собаке", "Отчет о коте", "Волонтер" -> {
                disableButtonsTemporarily();
                menu.sendMock(chatId);
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
                menu.chooseShelterReWrite(chatId);
            }
        }
    }

    /**
     * Temporarily disables buttons for a specified time.
     */
    private void disableButtonsTemporarily() {
        AtomicBoolean buttonEnabled = new AtomicBoolean(false);
        scheduler.schedule(() -> buttonEnabled.set(true), 30, TimeUnit.SECONDS);
    }
}