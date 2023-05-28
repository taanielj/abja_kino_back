package ttt.valiit.abja_kino_back.business.user.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
