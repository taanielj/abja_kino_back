package ttt.valiit.abja_kino_back;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import ttt.valiit.abja_kino_back.business.user.AdminService;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final AdminService adminService;

    @Bean
    public Clock clock() {
        //set system time zone
        return Clock.system(ZoneId.of("Europe/Tallinn"));
    }

    @Bean
        public CommandLineRunner initAdminUser() {
            return args -> adminService.initAdminUser();
    }


}
