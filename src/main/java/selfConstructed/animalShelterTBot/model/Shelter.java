package selfConstructed.animalShelterTBot.model;

import jakarta.persistence.*;
import lombok.*;
import selfConstructed.animalShelterTBot.model.Pet;

import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shelters")
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameOfTheShelter;
    private Long typePet;
    private String address;
    private String openingHours;
    private String contactInfo;
    @OneToMany(mappedBy = "shelter")
    private Set<Pet> pets;
}