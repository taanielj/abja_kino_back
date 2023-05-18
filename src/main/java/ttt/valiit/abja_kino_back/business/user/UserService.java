package ttt.valiit.abja_kino_back.business.user;

import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.business.login.LoginResponse;
import ttt.valiit.abja_kino_back.domain.user.User;
import ttt.valiit.abja_kino_back.domain.user.UserMapper;
import ttt.valiit.abja_kino_back.domain.user.UserRepository;
import ttt.valiit.abja_kino_back.domain.user.roleauthority.Role;
import ttt.valiit.abja_kino_back.domain.user.roleauthority.RoleRepository;
import ttt.valiit.abja_kino_back.validation.ValidationService;

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

    public void register(RegistrationRequest registrationRequest) {
        User user = userMapper.toUser(registrationRequest);
        Role defaultRole = roleRepository.findByName("ROLE_CUSTOMER");
        user.setRole(defaultRole);
        userRepository.save(user);
    }

    public User findUserBy(String username, String password) {
        Optional<User> userOptional = userRepository.findUserBy(username, password);
        ValidationService.validateCorrectUserCredentials(userOptional);
        User user = userOptional.get();
        return user;
    }
    public LoginResponse login(String username, String password) {
        User user = userRepository.findUserBy(username, password);
        LoginResponse loginResponse = userMapper.toLoginResponse(user);
        return loginResponse;
    }
}
