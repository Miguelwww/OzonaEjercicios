package com.miguel.ejercicio1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class NodeConfiguration {

    @Bean
    public RouterFunction<ServerResponse> routes (NodeHandler nodeHandler){
        return RouterFunctions.route(RequestPredicates.GET("/nodes"), nodeHandler::all)
            .andRoute(RequestPredicates.POST("/nodes"), nodeHandler::create);
    }
}
