package selfConstructed.animalShelterTBot.keyboard;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.springframework.stereotype.Component;


@Component
public class Keyboard {
    public InlineKeyboardMarkup getTestInlineButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("КНОПКА").callbackData("callback");
        inlineKeyboardMarkup.addRow(button1);
        return inlineKeyboardMarkup;
    }
}