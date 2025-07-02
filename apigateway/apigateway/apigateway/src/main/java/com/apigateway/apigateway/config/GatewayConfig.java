package com.apigateway.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apigateway.apigateway.filter.AuthFilter;

@Configuration
public class GatewayConfig {

    @Autowired
    private AuthFilter authFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("auth-service", r -> r.path("/auth/**")
                .uri("lb://AUTH-SERVICE")) // no auth required for login

            .route("customer-service", r -> r.path("/customers/**")
                .filters(f -> f.filter((GatewayFilter) authFilter))
                .uri("lb://CUSTOMER-SERVICE"))

            .route("item-service", r -> r.path("/items/**")
                .filters(f -> f.filter((GatewayFilter) authFilter))
                .uri("lb://ITEM-SERVICE"))

            .route("sales-order-service", r -> r.path("/orders/**")
                .filters(f -> f.filter((GatewayFilter) authFilter))
                .uri("lb://SALES-ORDER-SERVICE"))

            .build();
    }
}
