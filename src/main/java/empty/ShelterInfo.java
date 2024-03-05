package empty;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Shelter info")
public class ShelterInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_shelter;
    private String nameOfTheShelter;
    private String adress;
    private String kindOfAnimal;
    private String openingHours;
    private String contactInfo;
}