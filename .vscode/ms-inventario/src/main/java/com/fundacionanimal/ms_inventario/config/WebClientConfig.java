package com.fundacionanimal.ms_inventario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig 
{
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        // Apunta al puerto del microservicio de donaciones (8081)
        return builder.baseUrl("http://localhost:8081/api").build();
    }
}
