package selfConstructed.animalShelterTBot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AnimalReport")
public class AnimalReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnimal;
    private String photo;
    private String animalDiet;
    private String generalInformation;
    private String changeInBehavior;
}
