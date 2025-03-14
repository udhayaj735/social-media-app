package com.socialmedia.socialmediaapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf(csrf->csrf.disable())
               .authorizeHttpRequests(authorize->authorize.requestMatchers(HttpMethod.GET,"v1/api/*").permitAll())
                .authorizeHttpRequests(authorize->authorize.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
            return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailService()
    {
        UserDetails udhaya= User.builder()
                .username("udhaya")
                .password(passwordEncoder().encode("udhaya"))
                .roles("USER").build();

        UserDetails admin= User.builder()
                .username("admin").
                password(passwordEncoder().encode("admin"))
                .roles("USER","ADMIN").build();
        return new InMemoryUserDetailsManager(udhaya,admin);
    }

}
