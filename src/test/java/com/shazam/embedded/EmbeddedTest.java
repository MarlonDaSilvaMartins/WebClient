package com.shazam.embedded;

import com.shazam.controller.v1.track.TrackController;
import com.shazam.controller.v1.track.TrackControllerFacade;
import com.shazam.repository.TrackRepository;
import com.shazam.track.TrackIntegration;
import com.shazam.track.TrackService;
import com.shazam.track.TrackServiceFacade;
import com.shazam.track.model.response.TrackServiceResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.shazam.embedded.embeddedstub.EmbeddedStub.trackEntityStub;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

//@WebFluxTest
@AutoConfigureDataMongo
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TrackService.class, TrackRepository.class})
//@ContextConfiguration(classes = {TrackController.class, TrackControllerFacade.class, TrackServiceFacade.class,
//        TrackService.class, TrackIntegration.class, TrackRepository.class})
@EnableReactiveMongoRepositories("com.shazam.repository")
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.4.5")
class EmbeddedTest {
//    @Autowired
//    WebTestClient webTestClient;

    @Autowired
    TrackService trackService;

    @Autowired
    TrackRepository trackRepository;

    @MockBean
    TrackIntegration trackIntegration;

    @BeforeEach
    void setUp(){
        var entity = trackEntityStub();

        trackRepository.save(entity).block();
    }

    @Test
    void  whenDeleteTrackReturnNothing(){
        String trackId = "54428397";

        StepVerifier.create(trackService.deleteTrack(trackId))
                .verifyComplete();

//        webTestClient.delete()
//                .uri((UriBuilder uriBuilder) -> uriBuilder.path("/v1/track/")
//                        .path(trackId)
//                        .build())
//                .exchange()
//                .expectStatus()
//                .is2xxSuccessful();

//        StepVerifier.create(trackService.findById(trackId))
//                .assertNext((TrackServiceResponse response)  -> assertNull(response))
//                .verifyComplete();

        var actual = trackService.findById(trackId).block();
        assertNull(actual);
    }
}
