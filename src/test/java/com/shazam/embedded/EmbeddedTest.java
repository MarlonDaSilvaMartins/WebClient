package com.shazam.embedded;

import com.shazam.controller.v1.track.TrackController;
import com.shazam.controller.v1.track.TrackControllerFacade;
import com.shazam.controller.v1.track.model.response.TrackControllerResponse;
import com.shazam.repository.TrackRepository;
import com.shazam.repository.entity.TrackEntity;
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
import reactor.test.StepVerifier;

import static com.shazam.embedded.embeddedstub.EmbeddedStub.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@AutoConfigureDataMongo
@WebFluxTest
@ContextConfiguration(classes = {TrackController.class, TrackControllerFacade.class, TrackServiceFacade.class,
        TrackService.class, TrackIntegration.class, TrackRepository.class})
@EnableReactiveMongoRepositories("com.shazam.repository")
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.4.5")
class EmbeddedTest {
    @Autowired
    WebTestClient webTestClient;

    @Autowired
    TrackRepository trackRepository;

    @MockBean
    TrackIntegration trackIntegration;

    @Test
    void  whenSaveTrackReturnTrackEntity(){
        String trackId = "54428397";
        var expectEntity = trackEntityStub();
        var expectController = trackControllerExpectedStub();
        var integrationResponse = trackIntegretionStub();

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
                .isEqualTo(expectController);

        StepVerifier.create(trackRepository.findById("54428397"))
                .assertNext((TrackEntity response) -> assertEquals(expectEntity, response))
                .verifyComplete();

        var actual = trackRepository.findById(trackId).block();
        assertEquals(expectEntity,actual);
    }

    @Test
    void  whenDeleteTrackReturnNothing(){
        String trackId = "54428397";
        var entity = trackEntityStub();

        trackRepository.save(entity).block();

        webTestClient.delete()
                .uri((UriBuilder uriBuilder) -> uriBuilder.path("/v1/track/")
                        .path(trackId)
                        .build())
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

        var actual = trackRepository.findById(trackId).block();
        assertNull(actual);
    }
}
