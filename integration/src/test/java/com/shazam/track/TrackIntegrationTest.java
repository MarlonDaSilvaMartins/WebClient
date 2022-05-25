package com.shazam.track;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import com.shazam.track.model.response.TrackIntegrationResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import static com.shazam.track.trackintegrationstub.TrackIntegrationStub.trackIntegrationExpectStub;
import static com.shazam.track.trackintegrationstub.TrackIntegrationStub.trackIntegrationStub;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = TrackIntegration.class)
class TrackIntegrationTest {
    @InjectMocks
    private TrackIntegration trackIntegration;

    private static ClientAndServer server;

    @BeforeAll
    static void startServer(){
        server = startClientAndServer();
    }

    @BeforeEach
    void setupClass() {
        WebClient webClient = WebClient.builder()
                .baseUrl(String.format("http://localhost:%d", server.getPort()))
                .build();

        trackIntegration = new TrackIntegration(webClient);
    }

    @AfterAll
    static void stopServer(){
        server.close();
    }

    @Test
    void whenFindTrackReturnArtistIntegration() throws JsonProcessingException {
        TrackIntegrationResponse expected = trackIntegrationExpectStub();

        HttpRequest request =  HttpRequest.request()
                .withPath("/tracks/details")
                .withQueryStringParameter("track_id", "54428397")
                .withMethod("GET");

        ObjectMapper mapper = new ObjectMapper();

        var body = mapper.writeValueAsString(trackIntegrationStub());

        HttpResponse response = HttpResponse.response(body)
                .withStatusCode(HttpStatus.OK.value())
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        server.when(request).respond(response);

        StepVerifier.create(trackIntegration.findTrack("54428397"))
                .assertNext((TrackIntegrationResponse integrationResponse) ->
                        assertEquals(expected, integrationResponse))
                .verifyComplete();
    }
}