package selfConstructed.animalShelterTBot.keyboard;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.springframework.stereotype.Component;


@Component
public class Keyboard {
    public InlineKeyboardMarkup getTestInlineButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Let's GO!").callbackData("callback");
        inlineKeyboardMarkup.addRow(button1);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getShelter() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Киски").callbackData("киски");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Песики").callbackData("песики");
        inlineKeyboardMarkup.addRow(button1, button2);
        return inlineKeyboardMarkup;
    }
}