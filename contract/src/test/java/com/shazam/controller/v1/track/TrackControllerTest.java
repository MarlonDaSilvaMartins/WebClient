package com.shazam.controller.v1.track;

import com.shazam.controller.v1.track.model.response.TrackControllerResponse;
import com.shazam.track.TrackService;
import com.shazam.track.TrackServiceFacade;
import com.shazam.track.model.response.TrackServiceResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.shazam.controller.v1.track.controllerStub.TrackControllerStub.trackControllerExpectedStub;
import static com.shazam.controller.v1.track.controllerStub.TrackControllerStub.trackControllerStub;


@WebFluxTest
@ContextConfiguration(classes = {TrackController.class, TrackControllerFacade.class, TrackServiceFacade.class})
class TrackControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    TrackService trackService;

    TrackServiceResponse trackServiceStub = TrackServiceResponse.
            builder()
            .id("54428397")
            .url("https://www.shazam.com/track/54428397/without-me")
            .subtitle("Eminem")
            .type("MUSIC")
            .title("Without Me")
            .build();

    TrackControllerResponse trackControllerStub = TrackControllerResponse.
            builder()
            .id("54428397")
            .url("https://www.shazam.com/track/54428397/without-me")
            .subtitle("Eminem")
            .type("MUSIC")
            .title("Without Me")
            .build();

    @Test
    void  whenFindTrackReturnTrackController(){
        String trackId = "54428397";
        var expect = trackControllerExpectedStub();
        var controllerResponse = trackControllerStub();

        Mockito.when(trackService.findById(trackId))
                .thenReturn(Mono.just(trackServiceStub));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/track/")
                        .path(trackId)
                        .build())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(TrackControllerResponse.class)
                .isEqualTo(expect);
    }
}