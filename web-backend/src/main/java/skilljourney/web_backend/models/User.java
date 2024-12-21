package skilljourney.web_backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "user_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le nom d'utilisateur est obligatoire")
    @NotBlank(message = "Vueillez rentrer un nom valide")
    private String username;

    @NotNull(message = "Le mot de passe est obligatoire")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    @NotBlank(message = "Vueillez rentrer un mot de passe valide")
    private String password;

    @NotNull(message = "L'adresse email est obligatoire")
    @Email(message = "L'adresse email doit être valide")
    @Column(unique = true)
    private String email;
}
