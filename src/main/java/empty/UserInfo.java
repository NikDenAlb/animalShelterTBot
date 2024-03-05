package empty;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String fullName;
    private Long adult;
    private String passportDetails;
    private String adress;
    private String phoneNumber;
    private Long userStatus;
    private String idAnimal;
    private String trialPeriod;
}