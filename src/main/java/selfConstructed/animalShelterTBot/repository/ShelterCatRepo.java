package selfConstructed.animalShelterTBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import selfConstructed.animalShelterTBot.model.cat.ShelterCat;

public interface ShelterCatRepo extends JpaRepository<ShelterCat, String> {
}