package com.app.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("file-store", r -> r.path("/files/**")
                        .uri("http://localhost:8081"))

                .route("file-analysis", r -> r.path("/analysis/**")
                        .uri("http://localhost:8082"))

                .build();
    }
}
