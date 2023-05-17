package ttt.valiit.abja_kino_back.business.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ttt.valiit.abja_kino_back.domain.user.User;

import java.io.Serializable;

/**
 * A DTO for the {@link User} entity
 */
@Data
public class RegistrationRequest implements Serializable {
    @Size(max = 255)
    @NotNull
    private final String username;
    @Size(max = 255)
    @NotNull
    @Min(value = 8, message = "Password must be at least 8 characters long")
    private final String password;
    @Size(max = 255)
    @NotNull
    @Email(message = "Email should be valid")
    private final String email;
}