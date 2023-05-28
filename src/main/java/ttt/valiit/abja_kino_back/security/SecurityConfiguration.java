package ttt.valiit.abja_kino_back.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private static final String ROLE_ADMIN = "ADMIN";


    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfiguration(UserDetailsServiceImpl userDetailsService) {

        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {


        return new BCryptPasswordEncoder(13);
    }



    @Order(1)
    public SecurityFilterChain swaggerSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/swagger-ui/index.html").hasRole(ROLE_ADMIN)
                .and()
                .formLogin()
                .and()
                .httpBasic().disable()
                .csrf().disable();

        return http.build();

    }

    @Order(2)
    @Bean
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        String MOVIE_URL = "/api/v1/movie/*";

        http


                .authorizeHttpRequests()
                // UserController
                .requestMatchers(HttpMethod.POST, "/api/v1/user/*").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/user/*").hasAnyRole(ROLE_ADMIN)




                // MovieController
                .requestMatchers(HttpMethod.GET, MOVIE_URL).permitAll()
                .requestMatchers(HttpMethod.POST, MOVIE_URL).hasRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.PUT, MOVIE_URL).hasRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.DELETE, MOVIE_URL).hasRole(ROLE_ADMIN)
                .requestMatchers("/api/v1/movie/admin-summary").hasRole(ROLE_ADMIN)

                // SeanceController
                .requestMatchers(HttpMethod.GET, "/api/v1/seance/*").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/seance/*").hasRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.PUT, "/api/v1/seance/*").hasRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/api/v1/seance/*").hasRole(ROLE_ADMIN)
                .requestMatchers("/api/v1/seance/admin-summary").hasRole(ROLE_ADMIN)

                // Ticket(Type)Controller
                .requestMatchers(HttpMethod.GET, "/api/v1/ticket/type/*").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/ticket/*").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/v1/ticket/*").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/v1/ticket/*").hasAnyRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/api/v1/ticket/*").hasAnyRole(ROLE_ADMIN)

                // GenreController
                .requestMatchers(HttpMethod.GET, "/api/v1/genre/*").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/genre/*").hasRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.PUT, "/api/v1/genre/*").hasRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/api/v1/genre/*").hasRole(ROLE_ADMIN)

                // RoomController
                .requestMatchers(HttpMethod.GET, "/api/v1/room/*").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/room/*").hasRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.PUT, "/api/v1/room/*").hasRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/api/v1/room/*").hasRole(ROLE_ADMIN)


                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .csrf().disable();

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


}
