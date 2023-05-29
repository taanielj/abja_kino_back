package ttt.valiit.abja_kino_back.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
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

        http
                .authorizeHttpRequests()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic().disable()
                .csrf().disable();

        return http.build();
    }

    /*@Bean
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        String roleAdmin = "ADMIN";
        String[] paths = {
                "/api/v1/movie/**",
                "/api/v1/genre/**",
                "/api/v1/seance/**",
                "/api/v1/ticket/type/**",
                "/api/v1/room/**",
        };

        http
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/api/v1/user/login", "/api/v1/user/register").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").hasRole(roleAdmin)
                .requestMatchers(HttpMethod.GET, "/api/v1/movie/admin-summary").hasRole(roleAdmin)
                .requestMatchers(HttpMethod.GET, "/api/v1/seance/admin-summary").hasRole(roleAdmin)
                .requestMatchers(HttpMethod.GET, "/api/v1/ticket/type/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/ticket/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/v1/ticket/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/v1/room/**").authenticated()

                .requestMatchers(HttpMethod.GET, paths).permitAll()
                .requestMatchers(HttpMethod.POST, paths).hasRole(roleAdmin)
                .requestMatchers(HttpMethod.PUT, paths).hasRole(roleAdmin)
                .requestMatchers(HttpMethod.DELETE, paths).hasRole(roleAdmin)
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .csrf().disable();

        return http.build();
    }*/

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


}


