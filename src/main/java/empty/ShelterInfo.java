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