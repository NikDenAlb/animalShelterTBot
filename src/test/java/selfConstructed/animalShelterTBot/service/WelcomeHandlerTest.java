package selfConstructed.animalShelterTBot.service;

import com.pengrad.telegrambot.TelegramBot;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import selfConstructed.animalShelterTBot.keyboard.Keyboard;

public class WelcomeHandlerTest {
    @Mock
    private Logger logger;
    @Mock
    private TelegramBot telegramBot;
    @Mock
    private Keyboard keyboard;
    private WelcomeHandler welcomeHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        welcomeHandler = new WelcomeHandler(telegramBot, keyboard);
    }
}
