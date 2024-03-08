package selfConstructed.animalShelterTBot.listner;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import selfConstructed.animalShelterTBot.messageHandler.MsgHandler;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;

    private final MsgHandler messageHandler;


    public TelegramBotUpdatesListener(TelegramBot telegramBot, MsgHandler messageHandler) {
        this.telegramBot = telegramBot;
        this.messageHandler = messageHandler;
    }


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);

    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            Message message = update.message();
            CallbackQuery callbackQuery = update.callbackQuery();
            if (message != null) {
                messageHandler.handleMessage(message);
            }
            if (callbackQuery != null) {
                messageHandler.handleCallBack(callbackQuery);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }


    /**
     * processing message from user
     */
//    private void handleMessage(Message message) {
//        Long chatId = message.chat().id();
//        String text = message.text();
//        if ("/start".equals(text)) {
//            sendWelcomeMessage(chatId);
//        }
//    }

    /**
     * welcome message
     * added inline keyboard
     * sending welcome message to user with test button
     */
//    private void sendWelcomeMessage(long chatId) {
//        приветственное сообщение
//        String welcomeMessage = "Добро пожаловать! Я бот."
//                + '\n' + "Для начала работы нажмите кнопку \uD83D\uDC47";
//        добавление клавиатуры
//        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getTestInlineButton();
//        отправка сообщения в чат
//        telegramBot.execute(new SendMessage(chatId, welcomeMessage).replyMarkup(inlineKeyboardMarkup));
//        logger.info("Sending welcome message to chat {}: {}", chatId, welcomeMessage);
//    }
}
