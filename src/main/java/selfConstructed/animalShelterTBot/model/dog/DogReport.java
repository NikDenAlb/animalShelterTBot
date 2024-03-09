package selfConstructed.animalShelterTBot.model.dog;

import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dog_report")
public class DogReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String photo;
    private String dogDiet;
    private String changeInBehavior;
    private String generalInformation;
    @OneToOne
    @JoinColumn(name = "user_id")
    UserDog userDog;
}
