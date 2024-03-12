package selfConstructed.animalShelterTBot.keyboard;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.springframework.stereotype.Component;


@Component
public class Keyboard {
    public InlineKeyboardMarkup getTestInlineButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Начнем работу").callbackData("Начнем работу");
        inlineKeyboardMarkup.addRow(button1);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getShelter() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Коты").callbackData("Коты");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Собаки").callbackData("Собаки");
        inlineKeyboardMarkup.addRow(button1, button2);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getMenuAboutShelter() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Узнать информацию о приюте").callbackData("Информация");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Как взять животное").callbackData("Как взять");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Прислать отчет о питомце").callbackData("Отчет");
        inlineKeyboardMarkup.addRow(button1);
        inlineKeyboardMarkup.addRow(button2);
        inlineKeyboardMarkup.addRow(button3);
        return inlineKeyboardMarkup;
    }
}