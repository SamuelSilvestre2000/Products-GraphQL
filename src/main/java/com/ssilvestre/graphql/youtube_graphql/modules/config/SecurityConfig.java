package com.ssilvestre.graphql.youtube_graphql.modules.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig{
    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http.authorizeRequests()
    //         .antMatchers("/graphiql", "/graphql/**").permitAll() // Permitir todos os acessos para /graphiql e /graphql
    //         .anyRequest().authenticated();
    // }
    
    
    @Bean
public InMemoryUserDetailsManager userDetailsManager() {

    UserDetails user = User .withDefaultPasswordEncoder() // (1)
            .username("user")
            .password("password")
            .roles("USER")
            .build();

    UserDetails admin = User.withDefaultPasswordEncoder() // (2)
            .username("admin")
            .password("password")
            .roles("USER","ADMIN")
            .build();

    return new InMemoryUserDetailsManager(user, admin); // (3)
}

    @Bean
public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    return http
            .csrf(csrf -> csrf.disable()) // (1)
            .authorizeRequests( auth -> {
                auth.anyRequest().authenticated(); // (2)
            })
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // (3)
            .httpBasic(Customizer.withDefaults()) // (4)
            .build();
}
}
