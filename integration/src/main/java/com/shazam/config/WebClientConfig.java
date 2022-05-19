package com.shazam.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .baseUrl("https://shazam-core.p.rapidapi.com/v1")
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .defaultHeader("x-rapidapi-host", "shazam-core.p.rapidapi.com")
                .defaultHeader("x-rapidapi-key", "cbe4664fbamsh6ce4d0f7ffe04c9p1ba2cbjsn1f328399f35b")
                .build();
    }
}
