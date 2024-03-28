package selfConstructed.animalShelterTBot.service.messageHandler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.DeleteMessages;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import selfConstructed.animalShelterTBot.model.User;
import selfConstructed.animalShelterTBot.repository.ReportRepository;
import selfConstructed.animalShelterTBot.repository.UserRepository;
import selfConstructed.animalShelterTBot.service.*;

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
    private final ReportRepository reportRepository;
    private final ProcessReport processReport;

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
        String photoCaption = message.caption(); // Получаем подпись к фотографии, если есть
        logger.info("Получено сообщение от пользователя {}: {}", chatId, text);
        if ("/start".equals(text) && userIsPresent(chatId)) {
            menu.chooseShelterNew(chatId);
        } else {
            User user = new User();
            user.setChatId(chatId);
            userRepository.save(user);
            welcomeHandler.sendWelcomeMessage(chatId);
        }
//        if (userRepository.findByStatusAdopted(chatId)) {
//            if (text != null) {
//                logger.info("Получено текстовое сообщение от пользователя {}: {}", chatId, text);
//
//        Добавьте здесь обработку сообщений о рационе, общем самочувствии и изменениях в поведении
//            }
//            if (message.photo() != null && message.photo().length > 0) {
//                logger.info("Получена фотография от пользователя {}: {}", chatId, message.photo()[0].fileId());
//                reportRepository.savePhoto();
//            }
//        }
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
                isMessageIdNotNull(chatId);
                menu.getShelterMenuCatsNew(chatId);
            }
            case "Собаки" -> {
                isMessageIdNotNull(chatId);
                menu.getShelterMenuDogsNew(chatId);
            }
            case "Информация о приюте для собак" -> {
                if (menu.getMessageId() != null) {
                    DeleteMessage deleteMessage = new DeleteMessage(chatId, menu.getMessageId());
                    telegramBot.execute(deleteMessage);
                }
                disableButtonsTemporarily();
                shelterInfoHandler.shelterDogInfo(chatId);
            }
            case "Информация о приюте для котов" -> {
                if (menu.getMessageId() != null) {
                    DeleteMessage deleteMessage = new DeleteMessage(chatId, menu.getMessageId());
                    telegramBot.execute(deleteMessage);
                }
                disableButtonsTemporarily();
                shelterInfoHandler.shelterCatInfo(chatId);
            }
            case "Как взять собаку", "Как взять кота" -> {
                if (menu.getMessageId() != null) {
                    DeleteMessage deleteMessage = new DeleteMessage(chatId, menu.getMessageId());
                    telegramBot.execute(deleteMessage);
                }
                disableButtonsTemporarily();
                shelterAdoptionInfo.sendAdoptionInfo(chatId, callbackQuery);
            }
            case "Возврат в меню коты" -> {
                DeleteMessage deleteMessage = new DeleteMessage(chatId, messagesId.size() - 1);
                telegramBot.execute(deleteMessage);
                menu.getShelterMenuCatsNew(chatId);
            }
            case "Возврат в меню собаки" -> {
                DeleteMessage deleteMessage = new DeleteMessage(chatId, messagesId.size() - 1);
                telegramBot.execute(deleteMessage);
                menu.getShelterMenuDogsNew(chatId);
            }
            case "Назад" -> {
                DeleteMessages deleteMessages = new DeleteMessages(chatId, messagesId.stream()
                        .mapToInt(Integer::intValue)
                        .toArray());
                telegramBot.execute(deleteMessages);
                disableButtonsTemporarily();
                menu.chooseShelterNew(chatId);
            }
            case "Отчет о собаке", "Отчет о коте" -> {
                processReport.processReportCallBack(chatId, callbackQuery);
            }
            case "Волонтер", "Приютить кота", "Приютить собаку" -> {
                disableButtonsTemporarily();
                menu.sendMock(chatId);
            }
            default -> {
                disableButtonsTemporarily();
                menu.chooseShelterReWrite(chatId);
            }
        }
    }

    private void isMessageIdNotNull(Long chatId) {
        if (welcomeHandler.getMessageId() != null) {
            DeleteMessage deleteMessage = new DeleteMessage(chatId, welcomeHandler.getMessageId());
            telegramBot.execute(deleteMessage);
        } else if (menu.getMessageId() != null) {
            DeleteMessage deleteMessage = new DeleteMessage(chatId, menu.getMessageId());
            telegramBot.execute(deleteMessage);
        }
        disableButtonsTemporarily();
    }

    /**
     * Temporarily disables buttons for a specified time.
     */
    private void disableButtonsTemporarily() {
        AtomicBoolean buttonEnabled = new AtomicBoolean(false);
        scheduler.schedule(() -> buttonEnabled.set(true), 30, TimeUnit.SECONDS);
    }

    private boolean userIsPresent(long chatId) {
        return userRepository.findByChatId(chatId) != null;
    }
}