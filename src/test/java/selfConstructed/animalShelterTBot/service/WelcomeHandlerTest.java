package selfConstructed.animalShelterTBot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import selfConstructed.animalShelterTBot.keyboard.Keyboard;
import selfConstructed.animalShelterTBot.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WelcomeHandlerTest {
    @Mock
    private TelegramBot telegramBot;
    @Mock
    private Keyboard keyboard;
    private UserRepository userRepository;
    private WelcomeHandler welcomeHandler;
    @Captor
    ArgumentCaptor<SendMessage> sendMessageCaptor;//создаем капчу для аргумента передаваемого в метод

    @BeforeEach
    public void setUp() {
        welcomeHandler = new WelcomeHandler(telegramBot, keyboard, userRepository);
    }


    @Test
    public void shouldReturnWelcomeMessage() {
        long chatId = 123456L;
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        //поведение клавиатуры - когда класс киборд вызывает метод гетТестКиборд,
        //то возвращает новый объект инлайнкиборд
        when(keyboard.getStartInlineButton()).thenReturn(inlineKeyboardMarkup);

        welcomeHandler.sendWelcomeMessage(chatId);

        verify(telegramBot, times(1)).execute(sendMessageCaptor.capture());

        SendMessage sendMessage = sendMessageCaptor.getValue();

        assertThat(sendMessage.getClass()).isEqualTo(SendMessage.class);
    }
}

