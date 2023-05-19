package ttt.valiit.abja_kino_back.business.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ttt.valiit.abja_kino_back.business.user.dto.LoginResponse;
import ttt.valiit.abja_kino_back.business.user.dto.RegistrationRequest;


@RequestMapping("/user")
@RestController
@Validated
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @Operation(summary = "Registreerimine. Tagastab userId ja roleName",
            description = """
                    Süsteemis luuakse uus kasutaja.
                    Kui kasutajanimi on juba olemas vistakse viga errorCode'ga """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Kasutajanimi on juba olemas")})
    public LoginResponse registration(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return userService.register(registrationRequest);
    }

    @GetMapping("/login")
    @Operation(summary = "Sisse logimine. Tagastab userId ja roleName",
            description = """
                    Süsteemist otsitakse username ja password abil kasutajat.
                    Kui vastet ei leita vistakse viga errorCode'ga """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "Vale kasutajanimi või parool")})
    public LoginResponse login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }


}
