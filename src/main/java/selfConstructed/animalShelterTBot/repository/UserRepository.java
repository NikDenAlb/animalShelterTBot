package selfConstructed.animalShelterTBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import selfConstructed.animalShelterTBot.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User findByChatId(long chatId);

    @Query(value = "SELECT * FROM users WHERE status_adopted = true AND " +
            "user_type = 'User' AND chat_id = :chatId", nativeQuery = true)
    Optional<User> findByStatusAdopted(@Param("chatId") Long chatId);
}