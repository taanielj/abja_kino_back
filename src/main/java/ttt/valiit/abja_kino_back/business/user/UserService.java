package ttt.valiit.abja_kino_back.business.user;

import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.domain.user.User;
import ttt.valiit.abja_kino_back.domain.user.UserMapper;
import ttt.valiit.abja_kino_back.domain.user.UserRepository;
import ttt.valiit.abja_kino_back.domain.user.roleauthority.Role;
import ttt.valiit.abja_kino_back.domain.user.roleauthority.RoleRepository;

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
        user.setRole(roleRepository.findByName("ROLE_CUSTOMER"));
        userRepository.save(userMapper.toUser(registrationRequest));

    }
}
