package ttt.valiit.abja_kino_back.business.user;

import org.springframework.web.bind.annotation.*;


@RequestMapping("/user")
@RestController

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void registration(@RequestBody RegistrationRequest registrationRequest) {
        userService.register(registrationRequest);
    }


}
