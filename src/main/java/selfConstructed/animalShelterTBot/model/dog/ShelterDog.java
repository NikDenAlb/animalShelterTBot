package selfConstructed.animalShelterTBot.model.dog;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shelters_dog")
public class ShelterDog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameOfTheShelter;
    private String address;
    private String openingHours;
    private String contactInfo;
//    @OneToMany(mappedBy = "shelterDog")
//    private Set<Dog> dogs;
}
