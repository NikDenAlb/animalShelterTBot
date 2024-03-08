package selfConstructed.animalShelterTBot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ShelterAnimalInformation")
public class ShelterAnimal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnimal;
    private String nameAnimal;
    private String kindOfAnimal;
    private Long adult;
    private String breed;
    private String shelter;
}