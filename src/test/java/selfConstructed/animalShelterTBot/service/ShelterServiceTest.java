package selfConstructed.animalShelterTBot.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import selfConstructed.animalShelterTBot.repository.ShelterRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static selfConstructed.animalShelterTBot.Constants.SHELTER_LIST;


@ExtendWith(MockitoExtension.class)
class ShelterServiceTest {
    @Mock
    private ShelterRepository shelterRepositoryMock;

    @InjectMocks
    private ShelterService out;

    @Test
    void getShelters() {
        when(shelterRepositoryMock.findAll()).thenReturn(SHELTER_LIST);
        assertEquals(out.getAll(), SHELTER_LIST);
    }
    @Test
    void editShelterName() {
    }

}