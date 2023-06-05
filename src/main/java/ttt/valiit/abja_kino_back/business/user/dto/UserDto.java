package ttt.valiit.abja_kino_back.business.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ttt.valiit.abja_kino_back.business.user.User;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */

@Setter
@Getter
public class UserDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 255)
    String username;
    @NotNull
    @Size(max = 255)
    String email;
    @NotNull
    @Size(max = 255)
    String roleName;
    @NotNull
    String status;
    Integer boughtTickets;
}
