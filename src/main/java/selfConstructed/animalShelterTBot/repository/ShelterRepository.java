package selfConstructed.animalShelterTBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import selfConstructed.animalShelterTBot.model.Shelter;

public interface ShelterRepository extends JpaRepository<Shelter,String> {
}
