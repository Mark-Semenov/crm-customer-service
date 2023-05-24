//package ru.bwforum.mark.customer.service.api.config;
//
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
//import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig {
//
//    @Value("${keycloak.url}")
//    private String jwk;
//
//    @Bean
//    public SecurityWebFilterChain config(ServerHttpSecurity http) {
//        http
//                .authorizeExchange()
//                .pathMatchers("/api/**")
//                .hasRole("ADMIN")
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
//
//        return http.build();
//    }
//
//
//    @Bean
//    ReactiveJwtDecoder jwtDecoder() {
//        return NimbusReactiveJwtDecoder.withJwkSetUri(jwk).build();
//    }
//
//}
