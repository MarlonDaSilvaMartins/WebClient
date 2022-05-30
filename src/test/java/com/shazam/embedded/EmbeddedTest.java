package com.shazam.embedded;

import com.shazam.repository.TrackRepository;
import com.shazam.track.TrackIntegration;
import com.shazam.track.TrackService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriBuilder;
import reactor.test.StepVerifier;

import static com.shazam.embedded.embeddedstub.EmbeddedStub.trackEntityStub;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TrackService.class, TrackRepository.class})
@EnableReactiveMongoRepositories("com.shazam.repository")
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.4.3")
class EmbeddedTest {
//    @Autowired
//    WebApplicationContext webApplicationContext;
//
//    WebTestClient webTestClient;

    @Autowired
    TrackService trackService;

    @Autowired
    TrackRepository trackRepository;

    @MockBean
    TrackIntegration trackIntegration;

    @BeforeEach
    void setUp(){
//        this.webTestClient =  WebTestClient.bindToApplicationContext(webApplicationContext).configureClient().build();

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

        StepVerifier.create(trackService.findById(trackId))
                .verifyComplete();
    }
}
