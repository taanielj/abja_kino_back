package ttt.valiit.abja_kino_back.business.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ttt.valiit.abja_kino_back.domain.user.User;

import java.io.Serializable;

/**
 * A DTO for the {@link User} entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest implements Serializable {
    @Size(max = 255)
    @NotNull
    private String username;
    @Size(max = 255)
    @NotNull
    @Min(value = 8, message = "Parool peab olema vähemalt 8 tähemärki")
    private String password;
    @Size(max = 255)
    @NotNull
    @Email(message = "Email peab olema korrektne")
    private String email;


}
