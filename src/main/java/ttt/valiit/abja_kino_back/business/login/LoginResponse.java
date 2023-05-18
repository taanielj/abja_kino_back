package ttt.valiit.abja_kino_back.business.login;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class LoginResponse implements Serializable {
    @Size(max = 255)
    @NotNull
    private Integer userId;
    @Size(max = 255)
    @NotNull
    private String roleName;

}