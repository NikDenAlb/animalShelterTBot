package empty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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