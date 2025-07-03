package com.playita.config;

import com.playita.security.UsuarioDetailsServiceImpl;
import com.playita.security.LoginSuccessHandler;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UsuarioDetailsServiceImpl usuarioDetailsService;
    private final LoginSuccessHandler loginSuccessHandler;

    public SecurityConfig(UsuarioDetailsServiceImpl usuarioDetailsService,
                          LoginSuccessHandler loginSuccessHandler) {
        this.usuarioDetailsService = usuarioDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 🔓 Desactiva CSRF solo para APIs internas que reciben fetch/AJAX (como tu "/api/clientes/crear-rapido")
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/**")
            )

            // 🔐 Reglas de autorización
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/auth/**", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/api/**").permitAll() // ✔ Permitir acceso a /api/clientes/crear-rapido
                .requestMatchers("/dashboard/admin/**").hasRole("ADMIN")
                .requestMatchers("/dashboard/vendedor/**").hasRole("VENDEDOR")
                .anyRequest().authenticated()
            )

            // 🔐 Login personalizado
            .formLogin(login -> login
                .loginPage("/auth/login")
                .loginProcessingUrl("/login")
                .successHandler(loginSuccessHandler)
                .failureUrl("/auth/login?error=true")
                .permitAll()
            )

            // 🔓 Logout personalizado
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login?logout")
                .permitAll()
            )

            // 🔐 Servicio de usuario personalizado
            .userDetailsService(usuarioDetailsService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // ✔ Contraseñas seguras
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
