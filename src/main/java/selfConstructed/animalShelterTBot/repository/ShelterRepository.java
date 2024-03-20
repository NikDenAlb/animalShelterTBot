package selfConstructed.animalShelterTBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import selfConstructed.animalShelterTBot.model.Shelter;

import java.util.Optional;

public interface ShelterRepository extends JpaRepository<Shelter,String> {
    @Query(value = "SELECT s FROM Shelter s where s.typePet = 2")
    Optional<Shelter> findByDogs();

    @Query(value = "SELECT s FROM Shelter s where s.typePet = 1")
    Optional<Shelter> findByCats();
}
