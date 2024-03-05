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
@Table(name = "Volunteer info")
public class VolunteerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVolunteer;
    private String fullName;
    private String adressShelter;
    private String contactDetails;
    private String openingHours;
}