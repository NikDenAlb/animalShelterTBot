package selfConstructed.animalShelterTBot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Bot0 messages")
public class Bot0message {
    @Id
    private Long message_type;
    private String message_text;
}
