package selfConstructed.animalShelterTBot.listner;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import selfConstructed.animalShelterTBot.service.messageHandler.MsgHandler;

import java.util.List;

/**
 * Telegram bot update listener.
 * Processes incoming updates and passes them to the message handler.
 */
@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;

    private final MsgHandler messageHandler;

    /**
     * Constructor for TelegramBotUpdatesListener.
     *
     * @param telegramBot    TelegramBot object
     * @param messageHandler MsgHandler object
     */
    public TelegramBotUpdatesListener(TelegramBot telegramBot, MsgHandler messageHandler) {
        this.telegramBot = telegramBot;
        this.messageHandler = messageHandler;
    }

    /**
     * Initializes the listener after construction.
     * Sets this object as an update listener for the Telegram bot.
     */
    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * Processes the list of updates from the Telegram bot.
     *
     * @param updates list of updates
     * @return CONFIRMED_UPDATES_ALL to confirm that all updates are processed
     */
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
}
