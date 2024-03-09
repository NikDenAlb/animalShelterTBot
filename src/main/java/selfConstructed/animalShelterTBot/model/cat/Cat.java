package selfConstructed.animalShelterTBot.model.cat;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameCat;
    private Long adult;
    private String breed;
//    @ManyToOne
//    @JoinColumn(name = "shelterCat_id")
//    private ShelterCat shelterCat;
}
