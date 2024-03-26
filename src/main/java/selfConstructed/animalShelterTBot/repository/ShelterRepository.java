package selfConstructed.animalShelterTBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import selfConstructed.animalShelterTBot.model.Shelter;

import java.util.Optional;
/**
 * Repository for working with Shelter objects in the database.
 * This interface extends JpaRepository to perform basic CRUD operations.
 * @author hlopachev, shinkevich
 */
public interface ShelterRepository extends JpaRepository<Shelter,String> {
    @Query(value = "SELECT s FROM Shelter s where s.typePet = :typePet")
    Optional<Shelter> findByDogs(@Param("typePet") String typePet);

    @Query(value = "SELECT s FROM Shelter s where s.typePet = :typePet")
    Optional<Shelter> findByCats(@Param("typePet") String typePet);
}
