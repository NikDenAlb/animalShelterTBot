package selfConstructed.animalShelterTBot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Bot0messages")
public class Bot0message {
    @Id
    private String message_type;
    private String message_text;
}