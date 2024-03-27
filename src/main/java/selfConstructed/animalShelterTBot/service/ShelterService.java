package selfConstructed.animalShelterTBot.service;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import selfConstructed.animalShelterTBot.model.Shelter;
import selfConstructed.animalShelterTBot.repository.ShelterRepository;

import java.util.Collection;
import java.util.Optional;

import static selfConstructed.animalShelterTBot.model.TypePet.Cat;
import static selfConstructed.animalShelterTBot.model.TypePet.Dog;

@Service
public class ShelterService {
    private final ShelterRepository shelterRepository;
    private final Logger logger = LoggerFactory.getLogger(ShelterService.class);

    public ShelterService(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    public Collection<Shelter> getAll() {
        logger.debug("Bot0message findAll");
        return shelterRepository.findAll();
    }

    public Optional<Shelter> editShelterName(String id, String shelterName) {
        if (shelterRepository.findById(id).isPresent()) {
            Shelter newShelter = shelterRepository.findById(id).get();
            newShelter.setNameOfTheShelter(shelterName);
            return Optional.of(shelterRepository.save(newShelter));
        }
        return Optional.empty();
    }

    /**
     * Put 2 standard shelters into DB if DB is empty
     */
    @PostConstruct
    void init() {
        if (shelterRepository.findAll().isEmpty()) {
            shelterRepository.save(new Shelter(1L, "Кошкин Дом", Cat, "Кукуево", "6:00-18:00", "Звоните нам"));
            shelterRepository.save(new Shelter(2L, "Будка Шарика", Dog, "Кукуевка", "9:00-21:00", "Пишите нам"));
        }

    }
}