package ttt.valiit.abja_kino_back.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ttt.valiit.abja_kino_back.security.jwt.JwtFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(13);
    }

    @Bean
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        String mainUrl = "/api/v1/**";
        String admin = "ADMIN";
        http
                .authorizeHttpRequests()
                .requestMatchers(
                        "/api/v1/movie/admin-summary",
                        "/api/v1/seance/admin-summary",
                        "/api/v1/user/admin-summary"
                ).hasRole(admin)
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/user/login", "/api/v1/user/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/ticket/type/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/ticket/*").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/v1/ticket/*").authenticated()
                .requestMatchers(HttpMethod.GET, mainUrl).permitAll()
                .requestMatchers(HttpMethod.POST, mainUrl).hasRole(admin)
                .requestMatchers(HttpMethod.PUT, mainUrl).hasRole(admin)
                .requestMatchers(HttpMethod.DELETE, mainUrl).hasRole(admin)
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
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


