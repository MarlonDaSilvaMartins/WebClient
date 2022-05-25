package com.shazam.controller.v1.track;

import com.shazam.controller.v1.track.model.response.TrackControllerResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import static com.shazam.controller.v1.track.controllerStub.TrackControllerStub.trackControllerExpectedStub;
import static com.shazam.controller.v1.track.controllerStub.TrackControllerStub.trackControllerStub;
import static org.mockito.Mockito.when;


@WebFluxTest
@ContextConfiguration(classes = {TrackController.class, TrackControllerFacade.class})
class TrackControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    TrackControllerFacade trackControllerFacade;

    @Test
    void  whenFindTrackReturnTrackController(){
        String trackId = "54428397";
        var expect = trackControllerExpectedStub();
        var controllerResponse = trackControllerStub();

        when(trackControllerFacade.findTrack(trackId))
                .thenReturn(Mono.just(controllerResponse));

        webTestClient.get()
                .uri((UriBuilder uriBuilder) -> uriBuilder.path("/v1/track/")
                        .path(trackId)
                        .build())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(TrackControllerResponse.class)
                .isEqualTo(expect);
    }

    @Test
    void  whenDeleteTrackReturnNothing(){
        String trackId = "54428397";

        when(trackControllerFacade.deleteTrack(trackId))
                .thenReturn(Mono.empty());

        webTestClient.delete()
                .uri((UriBuilder uriBuilder) -> uriBuilder.path("/v1/track/")
                        .path(trackId)
                        .build())
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }
}