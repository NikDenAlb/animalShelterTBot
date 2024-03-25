package selfConstructed.animalShelterTBot.keyboard;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.springframework.stereotype.Component;

/**
 * A class representing a keyboard for a Telegram bot.
 * Contains methods for creating different types of keyboards.
 *
 * @author shinkevich
 */
@Component
public class Keyboard {
    /**
     * Creates a keyboard with one "Начнем работу" button.
     *
     * @return InlineKeyboardMarkup object with the "Начнем работу" button
     */
    public InlineKeyboardMarkup getStartInlineButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Начнем работу ▶").callbackData("Начнем работу");
        inlineKeyboardMarkup.addRow(button1);
        return inlineKeyboardMarkup;
    }

    /**
     * Creates a keyboard with three buttons "Cats" and "Dogs" and "Back".
     *
     * @return InlineKeyboardMarkup object with the "Cats" and "Dogs" and "Back" buttons
     */
    public InlineKeyboardMarkup getShelter() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Коты \uD83D\uDC08").callbackData("Коты");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Собаки \uD83D\uDC15").callbackData("Собаки");
        inlineKeyboardMarkup.addRow(button1, button2);
        return inlineKeyboardMarkup;
    }

    /**
     * Creates a menu with buttons for getting shelter for DOGS information, adopting an animal,
     * and submitting a pet report.
     *
     * @return InlineKeyboardMarkup object with buttons for the menu
     */
    public InlineKeyboardMarkup getMenuAboutShelterDogs() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("\uD83D\uDEC8 Узнать информацию о приюте для собак \uD83D\uDEC8")
                .callbackData("Информация о приюте для собак");
        InlineKeyboardButton button2 = new InlineKeyboardButton("⁉ Как взять животное ⁉")
                .callbackData("Как взять собаку");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Прислать отчет о питомце \uD83D\uDCCB")
                .callbackData("Отчет о собаке");
        InlineKeyboardButton button4 = new InlineKeyboardButton("\uD83D\uDCF1 Позвать волонтера \uD83D\uDCF1")
                .callbackData("Волонтер");
        InlineKeyboardButton button5 = new InlineKeyboardButton("\uD83D\uDC3E Вернуться в меню приютов \uD83D\uDC3E")
                .callbackData("Назад");
        inlineKeyboardMarkup.addRow(button1);
        inlineKeyboardMarkup.addRow(button2);
        inlineKeyboardMarkup.addRow(button3);
        inlineKeyboardMarkup.addRow(button4);
        inlineKeyboardMarkup.addRow(button5);
        return inlineKeyboardMarkup;
    }

    /**
     * Creates a menu with buttons for getting shelter for CATS information, adopting an animal,
     * and submitting a pet report.
     *
     * @return InlineKeyboardMarkup object with buttons for the menu
     */
    public InlineKeyboardMarkup getMenuAboutShelterCats() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("\uD83D\uDEC8 Узнать информацию о приюте для котов \uD83D\uDEC8")
                .callbackData("Информация о приюте для котов");
        InlineKeyboardButton button2 = new InlineKeyboardButton("⁉ Как взять животное ⁉")
                .callbackData("Как взять кота");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Прислать отчет о питомце \uD83D\uDCCB")
                .callbackData("Отчет о коте");
        InlineKeyboardButton button4 = new InlineKeyboardButton("\uD83D\uDCF1 Позвать волонтера \uD83D\uDCF1")
                .callbackData("Волонтер");
        InlineKeyboardButton button5 = new InlineKeyboardButton("\uD83D\uDC3E Вернуться в меню приютов \uD83D\uDC3E")
                .callbackData("Назад");
        inlineKeyboardMarkup.addRow(button1);
        inlineKeyboardMarkup.addRow(button2);
        inlineKeyboardMarkup.addRow(button3);
        inlineKeyboardMarkup.addRow(button4);
        inlineKeyboardMarkup.addRow(button5);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getGoBackButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Назад")
                .callbackData("Назад");
        inlineKeyboardMarkup.addRow(button1);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup callVolunteer() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("\uD83D\uDCF1 Позвать волонтера \uD83D\uDCF1")
                .callbackData("Волонтер");
        inlineKeyboardMarkup.addRow(button1);
        return inlineKeyboardMarkup;
    }
}