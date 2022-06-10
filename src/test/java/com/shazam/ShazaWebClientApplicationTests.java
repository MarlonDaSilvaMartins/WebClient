package com.shazam;

import com.shazam.controller.v1.track.TrackController;
import com.shazam.controller.v1.track.TrackControllerFacade;
import com.shazam.controller.v1.track.model.response.TrackControllerResponse;
import com.shazam.repository.TrackRepository;
import com.shazam.track.TrackIntegration;
import com.shazam.track.TrackService;
import com.shazam.track.TrackServiceFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import static com.shazam.appstub.AppStub.*;
import static org.mockito.Mockito.when;

@AutoConfigureDataMongo
@WebFluxTest
@ContextConfiguration(classes = {TrackController.class, TrackControllerFacade.class, TrackServiceFacade.class,
        TrackService.class, TrackIntegration.class, TrackRepository.class})
@EnableReactiveMongoRepositories("com.shazam.repository")
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.4.5")
class ShazaWebClientApplicationTests {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    TrackService trackService;

    @MockBean
    TrackRepository trackRepository;

    @MockBean
    TrackIntegration trackIntegration;

    @Test
    void whenFindTrackByIdReturnTrackController(){
        String trackId = "54428397";
        var expect = trackControllerExpectedStub();
        var entity = trackEntityStub();

        when(trackRepository.findById(trackId))
                .thenReturn(Mono.just(entity));

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
    void whenFindTrackIntegrationReturnTrackController(){
        String trackId = "54428397";
        var expect = trackControllerExpectedStub();
        var integrationResponse = trackIntegrationResponseStub();

        when(trackRepository.findById(trackId))
                .thenReturn(Mono.empty());
        when(trackIntegration.findTrack(trackId))
                .thenReturn(Mono.just(integrationResponse));

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
}
