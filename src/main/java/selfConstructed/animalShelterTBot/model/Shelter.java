package selfConstructed.animalShelterTBot.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

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
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "pet_type")
    private TypePet typePet;
    private String address;
    private String openingHours;
    private String contactInfo;
}