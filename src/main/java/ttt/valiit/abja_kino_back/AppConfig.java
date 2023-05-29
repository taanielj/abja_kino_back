package ttt.valiit.abja_kino_back;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
public class AppConfig {


    @Bean
    public Clock clock() {
        //set system time zone
        return Clock.system(ZoneId.systemDefault());
    }
}
