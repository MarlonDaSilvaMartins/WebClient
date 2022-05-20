package com.shazam.track;

import com.shazam.track.model.response.TrackIntegrationResponse;
import com.shazam.exceptions.trackhandler.TrackErrorException;
import com.shazam.exceptions.trackhandler.TrackErrorResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class TrackIntegration {
    private final WebClient webClient;

    public Mono<TrackIntegrationResponse> findTrack(String trackId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/tracks/details")
                        .queryParam("track_id", trackId)
                        .build())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, (ClientResponse response) -> response
                        .bodyToMono(TrackErrorResponse.class).map(TrackErrorException::new))
                .bodyToMono(TrackIntegrationResponse.class);

    }
}

