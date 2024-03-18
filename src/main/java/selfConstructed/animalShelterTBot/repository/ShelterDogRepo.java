package selfConstructed.animalShelterTBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import selfConstructed.animalShelterTBot.model.dog.ShelterDog;

public interface ShelterDogRepo extends JpaRepository<ShelterDog, String> {
}
