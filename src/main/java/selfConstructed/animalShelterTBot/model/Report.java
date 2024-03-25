package selfConstructed.animalShelterTBot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private byte[] photo;
    private String diet;
    private String changeInBehavior;
    private String generalInformation;
    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
}