package selfConstructed.animalShelterTBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SheltersRepository extends JpaRepository<SheltersRepository, Long> {
    @Query(value = "SELECT s FROM Shelters s where s.type_animal = 2")
    Optional<SheltersRepository> findByDogs();

    @Query(value = "SELECT s FROM Shelters s where s.type_animal = 1")
    Optional<SheltersRepository> findByCats();
}
