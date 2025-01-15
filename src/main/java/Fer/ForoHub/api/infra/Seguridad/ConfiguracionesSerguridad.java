package Fer.ForoHub.api.infra.Seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfiguracionesSerguridad {

    @Autowired
    FiltroDeSeguridad filtroDeSeguridad;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // NOTA: .disable() sigue disponible, pero explícitamente se define
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll() // Permitir acceso sin autenticación solo al endpoint /login
                        .anyRequest().authenticated())// Requiere autenticación para cualquier otra solicitud
                        .addFilterBefore(filtroDeSeguridad, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class) // Registra el filtro antes del filtro de autenticación
                        .build();
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
