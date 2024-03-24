package selfConstructed.animalShelterTBot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import selfConstructed.animalShelterTBot.model.Shelter;
import selfConstructed.animalShelterTBot.repository.ShelterRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShelterInfoHandlerTest {
    @Mock
    private TelegramBot telegramBot;
    @Mock
    private ShelterRepository repository;
    @Mock
    private TextsService textsService;

    private ShelterInfoHandler shelterInfoHandler;

    @BeforeEach
    public void setUp() {
        shelterInfoHandler = new ShelterInfoHandler(telegramBot, repository, textsService);
    }

    @Test
    public void whenShelterDogInfoIsCalled_thenMessageIsSentTest() {
        long chatId = 123456L;
        Shelter shelter = new Shelter(1L, "Dog", 2L, "Address",
                "Time", "Contact");

        Optional<Shelter> expectedShelter = Optional.of(shelter);

        when(repository.findByDogs()).thenReturn(expectedShelter);
        when(textsService.getTextOrDefault("Address", "get key")).thenReturn("Address");

        shelterInfoHandler.shelterDogInfo(chatId);

        verify(telegramBot, times(1)).execute(any(SendMessage.class));
    }    @Test
    public void whenShelterCaInfoIsCalled_thenMessageIsSentTest() {
        long chatId = 123456L;
        Shelter shelter = new Shelter(1L, "Cat", 1L, "Address",
                "Time", "Contact");

        Optional<Shelter> expectedShelter = Optional.of(shelter);

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
