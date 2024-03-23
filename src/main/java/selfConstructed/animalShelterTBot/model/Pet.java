package selfConstructed.animalShelterTBot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String namePet;
    private Long typePet;
    private Long adult;
    private String breed;
    @ManyToOne
    @JoinColumn(name = "shelterCat_id")
    private Shelter shelter;
}