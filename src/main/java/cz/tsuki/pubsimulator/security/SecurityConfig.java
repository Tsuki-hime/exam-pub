package cz.tsuki.pubsimulator.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user1 = User.builder()
                .username("Bartender Ben")
                .password(passwordEncoder().encode("YouShallNotPass"))
                .roles("BARTENDER")
                .build();
        UserDetails user2 = User.builder()
                .username("Pirate King")
                .password(passwordEncoder().encode("Aye,myBooze"))
                .roles("DRUNK")
                .build();
        UserDetails user3 = User.builder()
                .username("Dracula")
                .password(passwordEncoder().encode("WeDrinkYourBlood"))
                .roles("DRUNK")
                .build();
        UserDetails user4 = User.builder()
                .username("Ranger Captain Velorana")
                .password(passwordEncoder().encode("IAmForsaken"))
                .roles("DRUNK")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, user3, user4);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                    corsConfig.addAllowedOrigin("*");
                    corsConfig.addAllowedHeader("*");
                    corsConfig.addAllowedMethod("*");
                    return corsConfig;
                }))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/api/users/*", "/api/summary/*").hasRole("BARTENDER")
                                .requestMatchers("/api/drink/menu", "/api/buy").hasRole("DRUNK"))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
