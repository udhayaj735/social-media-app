package com.socialmedia.socialmediaapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailService()
    {
        UserDetails udhaya= User.builder().username("udhaya").password("udhaya").roles("USER").build();

        UserDetails admin= User.builder().username("admin").password("admin").roles("USER","ADMIN").build();
        return new InMemoryUserDetailsManager(udhaya,admin);
    }


}
