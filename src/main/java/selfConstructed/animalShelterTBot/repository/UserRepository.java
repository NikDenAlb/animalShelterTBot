package selfConstructed.animalShelterTBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import selfConstructed.animalShelterTBot.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    Boolean existsByChatIdAndChatIdNotNull(long chatId);
    User getUserByChatId(long chatId);
}