package selfConstructed.animalShelterTBot.service;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import selfConstructed.animalShelterTBot.model.Shelter;
import selfConstructed.animalShelterTBot.repository.ShelterRepository;

import java.util.Collection;
import java.util.Optional;

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

    public Optional<Shelter> editMessage(Shelter shelter) {
        if (shelterRepository.findById(String.valueOf(shelter.getId())).isPresent()) {
            return Optional.of(shelterRepository.save(shelter));
        }
        return Optional.empty();
    }
    @PostConstruct
    void init() {
        if (shelterRepository.findAll().isEmpty()) {
            shelterRepository.save(new Shelter(1L, "Кошкин Дом", 1L, "Кукуево", "6:00-18:00", "Звоните нам",null));
            shelterRepository.save(new Shelter(2L, "Будка Шарика", 2L, "Кукуевка", "9:00-21:00", "Пишите нам",null));
        }

    }
}