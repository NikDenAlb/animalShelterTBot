package selfConstructed.animalShelterTBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import selfConstructed.animalShelterTBot.model.Pet;

public interface PetRepository extends JpaRepository<Pet,String> {
}
