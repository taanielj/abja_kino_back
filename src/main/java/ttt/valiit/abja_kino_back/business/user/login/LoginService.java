package ttt.valiit.abja_kino_back.business.user.login;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.business.user.UserService;
import ttt.valiit.abja_kino_back.domain.user.User;
import ttt.valiit.abja_kino_back.domain.user.UserMapper;

@Service
public class LoginService {
    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;


    public LoginResponse login(String username, String password) {
        User user = userService.findUserBy(username, password);
        LoginResponse loginResponse = userMapper.toLoginResponse(user);
        loginResponse.setToken("token");
        return loginResponse;
    }


}





