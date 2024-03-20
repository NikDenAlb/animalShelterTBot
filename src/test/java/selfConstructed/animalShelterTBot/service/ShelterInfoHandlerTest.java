package selfConstructed.animalShelterTBot.service;

import com.pengrad.telegrambot.TelegramBot;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import selfConstructed.animalShelterTBot.repository.ShelterRepository;

@ExtendWith(MockitoExtension.class)
public class ShelterInfoHandlerTest {
    @Mock
    private TelegramBot telegramBot;
    @Mock
    private ShelterRepository repository;
    private ShelterInfoHandler shelterInfoHandler;

    public void setUp() {
        shelterInfoHandler = new ShelterInfoHandler(telegramBot, repository);
    }
}
