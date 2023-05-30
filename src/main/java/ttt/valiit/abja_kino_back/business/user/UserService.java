package ttt.valiit.abja_kino_back.business.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.business.user.dto.LoginRequest;
import ttt.valiit.abja_kino_back.business.user.dto.LoginResponse;
import ttt.valiit.abja_kino_back.business.user.dto.RegistrationRequest;
import ttt.valiit.abja_kino_back.business.user.role.Role;
import ttt.valiit.abja_kino_back.business.user.role.RoleRepository;
import ttt.valiit.abja_kino_back.infrastructure.exception.InvalidCredentialsException;
import ttt.valiit.abja_kino_back.infrastructure.exception.UsernameExistsException;
import ttt.valiit.abja_kino_back.security.jwt.JwtUtil;

import java.util.List;

import static ttt.valiit.abja_kino_back.infrastructure.Error.INVALID_CREDENTIALS;
import static ttt.valiit.abja_kino_back.infrastructure.Status.ACTIVE;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;



    public LoginResponse register(RegistrationRequest registrationRequest) {

        User user = registerAndGetUser(registrationRequest);


        return getLoginResponse(user);
    }

    private User registerAndGetUser(RegistrationRequest registrationRequest) {
        if (userRepository.existsBy(registrationRequest.getUsername())) {
            throw new UsernameExistsException("Kasutajanimi on juba kasutusel");
        }

        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new UsernameExistsException("Email on juba kasutusel");
        }

        User user = userMapper.toUser(registrationRequest);
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setRole(getDefaultRole());
        user.setStatus(ACTIVE.getLetter());
        userRepository.save(user);
        return user;
    }

    public LoginResponse login(LoginRequest loginRequest) {

        User user = getUser(loginRequest);


        return getLoginResponse(user);
    }

    public List<UserDto> getAllUsers() {
        return userMapper.toUserDtoList(userRepository.findAll());
    }

    private Role getDefaultRole() {
        return roleRepository.findByName("CUSTOMER").orElseGet(() -> {
            Role role = new Role("CUSTOMER");
            return roleRepository.save(role);
        });
    }

    private LoginResponse getLoginResponse(User user) {
        UserDetails userDetails = new UserDetailsImpl(user);
        String token = jwtUtil.generateToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);
        LoginResponse loginResponse = userMapper.toLoginResponse(user);
        loginResponse.setToken(token);
        loginResponse.setRefreshToken(refreshToken);

        return loginResponse;
    }

    private User getUser(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseGet(() -> userRepository.findByEmail(loginRequest.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException(INVALID_CREDENTIALS.getMessage())));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException(INVALID_CREDENTIALS.getMessage());
        }
        return user;
    }



}
