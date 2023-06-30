package ru.bwforum.mark.customer.service.api.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRepository;

@Log4j2
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain config(ServerHttpSecurity http) {

        http
                .csrf()
                .disable()
                .authorizeExchange()
                .anyExchange()
                .hasAuthority("SCOPE_crm.api")
                .and()
                .oauth2ResourceServer()
                .jwt();

        return http.build();
    }


}
