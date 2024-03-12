package selfConstructed.animalShelterTBot.model.dog;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dogs")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameDog;
    private Long adult;
    private String breed;
    @ManyToOne
    @JoinColumn(name = "shelterDog_id")
    private ShelterDog shelterDog;
}