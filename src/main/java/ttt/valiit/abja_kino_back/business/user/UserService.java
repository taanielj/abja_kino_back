package ttt.valiit.abja_kino_back.business.user;

import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.business.user.dto.LoginResponse;
import ttt.valiit.abja_kino_back.business.user.dto.RegistrationRequest;
import ttt.valiit.abja_kino_back.business.user.role.Role;
import ttt.valiit.abja_kino_back.business.user.role.RoleRepository;
import ttt.valiit.abja_kino_back.infrastructure.exception.InvalidCredentialsException;
import ttt.valiit.abja_kino_back.infrastructure.exception.UsernameExistsException;

import java.util.Optional;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository, RoleRepository roleRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public LoginResponse register(RegistrationRequest registrationRequest) {
        if (userRepository.existsBy(registrationRequest.getUsername())) {
            throw new UsernameExistsException("Kasutajanimi on juba kasutusel");
        }

        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new UsernameExistsException("Email on juba kasutusel");
        }

        User user = userMapper.toUser(registrationRequest);
        Role defaultRole = roleRepository.findByName("ROLE_CUSTOMER");
        user.setRole(defaultRole);
        userRepository.save(user);
        return login(registrationRequest.getUsername(), registrationRequest.getPassword());
    }

    public LoginResponse login(String username, String password) {
        User user = findUserBy(username, password);
        LoginResponse loginResponse = userMapper.toLoginResponse(user);
        loginResponse.setToken("token");
        return loginResponse;
    }

    private User findUserBy(String username, String password) {
        Optional<User> userOptional = userRepository.findUserBy(username, password);
        return userOptional.orElseThrow(
                () -> new InvalidCredentialsException("Vale kasutajanimi või parool")
        );
    }

}
