package selfConstructed.animalShelterTBot.model.dog;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_dog")
public class UserDog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private String fullName;
    private Long adult;
    private String address;
    private String phoneNumber;
    private boolean isAdopted;
//    @OneToOne
//    @JoinColumn(name = "pet_id")
//    private Dog pet;
}