package selfConstructed.animalShelterTBot.model.cat;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_cat")
public class UserCat {
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
//    private Cat pet;
}