package selfConstructed.animalShelterTBot.model.cat;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shelters_Cat")
public class ShelterCat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameOfTheShelter;
    private String address;
    private String openingHours;
    private String contactInfo;
//    @OneToMany(mappedBy = "shelterCat")
//    private Set<Dog> cats;
}