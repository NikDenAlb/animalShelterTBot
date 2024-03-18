package selfConstructed.animalShelterTBot.model;

import jakarta.persistence.*;
import lombok.*;
import selfConstructed.animalShelterTBot.model.Pet;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private String fullName;
    private Long adult;
    private String address;
    private String phoneNumber;
    private Long isAdopted;
    @OneToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
}