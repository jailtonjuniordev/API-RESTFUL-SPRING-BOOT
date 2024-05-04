package br.com.jjdev.APIREST.infra.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                        .requestMatchers(HttpMethod.POST, "/users/").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/users/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/users/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/{id}").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/product/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/product/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/product/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/product/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/product/{id}").permitAll()

                        .requestMatchers(HttpMethod.POST, "/category/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/category/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/category/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/category/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/category/{id}").permitAll()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
