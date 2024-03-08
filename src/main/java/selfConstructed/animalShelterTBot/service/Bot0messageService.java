package selfConstructed.animalShelterTBot.service;

import selfConstructed.animalShelterTBot.model.Bot0message;

import java.util.Collection;
import java.util.Optional;

public interface Bot0messageService {
    Collection<Bot0message> getAll();

    Optional<Bot0message> editMessage(Bot0message bot0message);
}