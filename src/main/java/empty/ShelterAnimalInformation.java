package empty;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Shelter animal information")
public class ShelterAnimalInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnimal;
    private String nameAnimal;
    private String kindOfAnimal;
    private Long adult;
    private String breed;
    private String shelter;
}