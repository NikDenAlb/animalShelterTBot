package selfConstructed.animalShelterTBot.listner;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import selfConstructed.animalShelterTBot.keyboard.Keyboard;
import selfConstructed.animalShelterTBot.repository.Bot0Repository;

import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;

    private final Keyboard keyboard;
    private final Bot0Repository bot0Repository;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, Keyboard keyboard, Bot0Repository bot0Repository) {
        this.telegramBot = telegramBot;
        this.keyboard = keyboard;
        this.bot0Repository = bot0Repository;
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
            handleMessage(message);
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }


    /**
     * processing message from user
     */
    private void handleMessage(Message message) {
        Long chatId = message.chat().id();
        String text = message.text();
        if ("/start".equals(text)) {
            sendWelcomeMessage(chatId);
        }
    }

    /**
     * Дорабатывается ------
     * welcome message
     * added inline keyboard
     * sending welcome message to user with test button
     */
    private void sendWelcomeMessage(long chatId) {
        //приветственное сообщение
        String welcomeMessage = "Добро пожаловать! Я бот."
                + '\n' + "Для начала работы нажмите кнопку TEST";
        //добавление клавиатуры
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.getTestInlineButton();

        //отправка сообщения в чат

        /*Кнопка отключена, её нажатие блокирует работу бота
        telegramBot.execute(new SendMessage(chatId, welcomeMessage).replyMarkup(inlineKeyboardMarkup));*/

        telegramBot.execute(new SendMessage(chatId, bot0Repository.findById("НП").get().getMessage_text()));
        logger.info("Sending welcome message to chat {}: {}", chatId, welcomeMessage);
    }
}
