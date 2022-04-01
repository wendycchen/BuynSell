package com.cgi.config;

import com.cgi.filters.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration @RequiredArgsConstructor
public class GatewayConfig {


    private final JwtFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes().route("account-service", r -> r.path("/api/v1/account/**").filters(f -> f.filter(filter)).uri("lb://account-service"))
                .route("productMicroservice", r -> r.path("/productMicro/**").filters(f -> f.filter(filter)).uri("lb://productMicroservice"))
                .route("ordersMicroservice", r -> r.path("/ordersMicroservice/**").filters(f -> f.filter(filter)).uri("lb://ordersMicroservice"))
                .build();
    }

}