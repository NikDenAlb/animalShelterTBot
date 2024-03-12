package selfConstructed.animalShelterTBot.listner;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import selfConstructed.animalShelterTBot.messageHandler.MsgHandler;

import selfConstructed.animalShelterTBot.keyboard.Keyboard;
import selfConstructed.animalShelterTBot.repository.Bot0Repository;


import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;


    private final MsgHandler messageHandler;


    public TelegramBotUpdatesListener(TelegramBot telegramBot, MsgHandler messageHandler) {
        this.telegramBot = telegramBot;
        this.messageHandler = messageHandler;

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

            CallbackQuery callbackQuery = update.callbackQuery();
            if (message != null) {
                messageHandler.handleMessage(message);
            }
            if (callbackQuery != null) {
                messageHandler.handleCallBack(callbackQuery);
            }

            handleMessage(message);

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }


}
