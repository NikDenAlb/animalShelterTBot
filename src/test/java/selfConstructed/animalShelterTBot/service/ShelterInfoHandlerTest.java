package selfConstructed.animalShelterTBot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import selfConstructed.animalShelterTBot.model.Pet;
import selfConstructed.animalShelterTBot.model.Shelter;
import selfConstructed.animalShelterTBot.repository.ShelterRepository;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShelterInfoHandlerTest {
    @Mock
    private TelegramBot telegramBot;
    @Mock
    private ShelterRepository repository;
    private ShelterInfoHandler shelterInfoHandler;

    @BeforeEach
    public void setUp() {
        shelterInfoHandler = new ShelterInfoHandler(telegramBot, repository);
    }

    @Test
    public void whenShelterDogInfoIsCalled_thenMessageIsSentTest() {
        Long chatId = 123456L;
        Shelter dogShelter = new Shelter(1L, "Dog", 2L, "Address",
                "Time", "Contact", Set.of(new Pet()));

        Optional<Shelter> expectedShelter = Optional.of(dogShelter);

        when(repository.findByDogs()).thenReturn(expectedShelter);

        shelterInfoHandler.shelterDogInfo(chatId);

        verify(telegramBot, times(1)).execute(any(SendMessage.class));


    }

    @Test
    public void whenNoShelterIsPresent_thenNoShelterMessageIsSent() {
        long chatId = 12345L;

        when(repository.findByDogs()).thenReturn(Optional.empty());

        shelterInfoHandler.shelterDogInfo(chatId);

        verify(telegramBot).execute(any(SendMessage.class));
    }
}
