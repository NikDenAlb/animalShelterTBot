package selfConstructed.animalShelterTBot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import selfConstructed.animalShelterTBot.keyboard.Keyboard;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WelcomeHandlerTest {
    @Mock
    private TelegramBot telegramBot;
    @Mock
    private Keyboard keyboard;
    private WelcomeHandler welcomeHandler;

    @BeforeEach
    public void setUp() {
        welcomeHandler = new WelcomeHandler(telegramBot, keyboard);
    }


    @Test
    public void shouldReturnWelcomeMessage() {
        long chatId = 123456L;
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        //поведение клавиатуры - когда класс киборд вызывает метод гетТестКиборд,
        //то возвращает новый объект инлайнкиборд
        when(keyboard.getTestInlineButton()).thenReturn(inlineKeyboardMarkup);

        welcomeHandler.sendWelcomeMessage(chatId);

        verify(telegramBot, times(1)).execute(any(SendMessage.class));

    }
}

