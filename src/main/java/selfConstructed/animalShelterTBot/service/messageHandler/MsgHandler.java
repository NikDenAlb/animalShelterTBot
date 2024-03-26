package selfConstructed.animalShelterTBot.service.messageHandler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.DeleteMessages;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import selfConstructed.animalShelterTBot.model.User;
import selfConstructed.animalShelterTBot.repository.UserRepository;
import selfConstructed.animalShelterTBot.service.MenuService;
import selfConstructed.animalShelterTBot.service.ShelterAdoptionInfo;
import selfConstructed.animalShelterTBot.service.ShelterInfoHandler;
import selfConstructed.animalShelterTBot.service.WelcomeHandler;

import java.util.ArrayList;
import java.util.List;
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
    private final TelegramBot telegramBot;
    private final MenuService menu;
    private final ShelterInfoHandler shelterInfoHandler;
    private final WelcomeHandler welcomeHandler;
    private final ShelterAdoptionInfo shelterAdoptionInfo;
    private final UserRepository userRepository;

    private final List<Integer> messagesId = new ArrayList<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * Processing a message from the user.
     *
     * @param message Message object
     */
    public void handleMessage(Message message) {
        Long chatId = message.chat().id();
        String text = message.text();
        logger.info("Получено сообщение от пользователя {}: {}", chatId, text);
        if ("/start".equals(text)) {
            welcomeHandler.sendWelcomeMessage(chatId);
        }
        if (userRepository.findByChatId(chatId) == null) {
            User user = new User();
            user.setChatId(chatId);
            userRepository.save(user);
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
        logger.info("Получен ответ от пользователя {}: {}", chatId, text);
        if (welcomeHandler.getMessageId() != null) {
            messagesId.add(welcomeHandler.getMessageId());
        }
        if (shelterInfoHandler.getMessageId() != null) {
            messagesId.add(shelterInfoHandler.getMessageId());
        }
        if (shelterAdoptionInfo.getMessageId() != null) {
            messagesId.add(shelterAdoptionInfo.getMessageId());
        }
        if (menu.getMessageId() != null) {
            messagesId.add(menu.getMessageId());
        }
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
                shelterAdoptionInfo.sendAdoptionInfo(chatId);
                menu.getShelterMenuDogsNew(chatId);
            }
            case "Как взять кота" -> {
                disableButtonsTemporarily();
                shelterAdoptionInfo.sendAdoptionInfo(chatId);
                menu.getShelterMenuCatsNew(chatId);
            }
            case "Назад" -> {
                DeleteMessages deleteMessages = new DeleteMessages(chatId, messagesId.stream()
                        .mapToInt(Integer::intValue).toArray());
                telegramBot.execute(deleteMessages);
                disableButtonsTemporarily();
                welcomeHandler.sendWelcomeMessage(chatId);
            }
            case "Отчет о собаке", "Отчет о коте", "Волонтер" -> {
                disableButtonsTemporarily();
                menu.sendMock(chatId);
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