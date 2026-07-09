package com.example.demo.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.web.server.ServerHttpSecurity;

import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration 
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            .csrf(csrf -> csrf.disable()) 
            
            // CONFIGURACIÓN DE CORS CORREGIDA PARA PERMITIR TOKENS
            .cors(cors -> cors.configurationSource(request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Arrays.asList("*")); 
                config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
                return config;
            }))
            
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())
            
            .authorizeExchange(exchange -> exchange
                .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll() 
                .pathMatchers("/api/usuarios/login", "/api/usuarios/registrar").permitAll()   
                .pathMatchers("/api/*/usuarios/login", "/api/*/usuarios/registrar").permitAll() 
                .pathMatchers("/api/notificaciones/**").permitAll()
                .pathMatchers("/api/alertas/**").permitAll()
                .pathMatchers("/api/reportes", "/api/*/reportes").permitAll()
                .anyExchange().authenticated() 
            );
        return http.build();
    }
}