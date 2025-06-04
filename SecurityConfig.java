package com.example.library.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.*;
 
@Configuration
public class SecurityConfig {
 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .httpBasic();
        return http.build();
    }
 
    @Bean
    public UserDetailsService users() {
        UserDetails librarian = User.withUsername("librarian")
            .password("{noop}lib123")
            .roles("LIBRARIAN")
            .build();
        return new InMemoryUserDetailsManager(librarian);
    }
}
