package ttt.valiit.abja_kino_back.business.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ttt.valiit.abja_kino_back.business.user.dto.RegistrationRequest;
import ttt.valiit.abja_kino_back.business.user.role.Role;
import ttt.valiit.abja_kino_back.business.user.role.RoleRepository;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.email}")
    private String adminEmail;





    public void initAdminUser() {
        User adminUser = userRepository.findByUsername(adminUsername).orElse(null);
        
        if (adminUser == null) {
            createAdminUser();
        } else {
            updateAdminPasswordIfNeeded(adminUser);
        }
    }
    
    private void createAdminUser() {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setUsername(adminUsername);
        registrationRequest.setPassword(adminPassword);
        registrationRequest.setEmail(adminEmail);
        
        User adminUser = userService.registerAndGetUser(registrationRequest);
        adminUser.setRole(getAdminRole());
        userRepository.save(adminUser);
    }
    
    private void updateAdminPasswordIfNeeded(User adminUser) {
        if (!passwordEncoder.matches(adminPassword, adminUser.getPassword())) {
            // Password in properties is different from stored password, update it
            adminUser.setPassword(passwordEncoder.encode(adminPassword));
            userRepository.save(adminUser);
        }
    }
    
    private Role getAdminRole() {
        return roleRepository.findByName("ADMIN").orElseGet(() -> {
            Role role = new Role("ADMIN");
            return roleRepository.save(role);
        });
    }



}
