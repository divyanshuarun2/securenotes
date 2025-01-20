package com.secure.notes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@EnableWebSecurity
//class = SpringBootWebSecurityConfiguration have pre-defined Bean...
public class SecurityConfig {
@Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                (requests) ->
                        requests.anyRequest().authenticated());
        http.csrf(csrf->csrf.disable());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager manager =
                new JdbcUserDetailsManager(dataSource);
        //(1st Way)
        {
        //(1st way) if the username is not present in memory
        // we will create a user with password and role using createUser() method.

        if (!manager.userExists("user1")) {
            UserDetails user1 = User.withUsername("user1").password("{noop}password1").roles("USER").build();

            manager.createUser(user1);
        }
    }
    //(2nd way)
    if(!manager.userExists("admin")) {
        manager.createUser(User.withUsername("admin")
                .password("{noop}adminpassword")
                .roles("ADMIN")
                .build());

    }
    return manager;
    }
}
