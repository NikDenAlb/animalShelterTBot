package selfConstructed.animalShelterTBot.model.cat;

import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cat_report")
public class CatReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String photo;
    private String catDiet;
    private String changeInBehavior;
    private String generalInformation;
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    UserCat userCat;
}
