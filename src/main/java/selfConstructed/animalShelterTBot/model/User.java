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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "user_type")
    private TypeUser typeUser;
    private String fullName;
    private Long adult;
    private String address;
    private String phoneNumber;
    private Boolean statusAdopted;
    @OneToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
}