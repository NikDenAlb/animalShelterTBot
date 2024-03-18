package selfConstructed.animalShelterTBot.keyboard;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.springframework.stereotype.Component;

/**
 * A class representing a keyboard for a Telegram bot.
 * Contains methods for creating different types of keyboards.
 */
@Component
public class Keyboard {
    /**
     * Creates a keyboard with one "Начнем работу" button.
     *
     * @return InlineKeyboardMarkup object with the "Начнем работу" button
     */
    public InlineKeyboardMarkup getTestInlineButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Начнем работу").callbackData("Начнем работу");
        inlineKeyboardMarkup.addRow(button1);
        return inlineKeyboardMarkup;
    }

    /**
     * Creates a keyboard with two buttons "Cats" and "Dogs".
     *
     * @return InlineKeyboardMarkup object with the "Cats" and "Dogs" buttons
     */
    public InlineKeyboardMarkup getShelter() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Коты").callbackData("Коты");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Собаки").callbackData("Собаки");
        inlineKeyboardMarkup.addRow(button1, button2);
        return inlineKeyboardMarkup;
    }

    /**
     * Creates a menu with buttons for getting shelter information, adopting an animal,
     * and submitting a pet report.
     *
     * @return InlineKeyboardMarkup object with buttons for the menu
     */
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