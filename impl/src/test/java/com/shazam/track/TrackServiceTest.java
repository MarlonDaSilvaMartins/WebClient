package com.shazam.track;

import com.shazam.repository.TrackRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.shazam.track.serviceStub.TrackServiceStub.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TrackService.class, TrackRepository.class, TrackIntegration.class})
class TrackServiceTest {

    @InjectMocks
    TrackService trackService;

    @Mock
    TrackRepository trackRepository;

    @Mock
    TrackIntegration trackIntegration;

    @Test
    void whenFindTrackByIdReturnTrackserviceResponse() {
        var trackEntity = trackEntityStub();
        var expected = trackServiceExpectedStub();

        when(trackRepository.findById("54428397"))
                .thenReturn(Mono.just(trackEntity));
        StepVerifier.create(trackService.findById("54428397"))
                .assertNext(response -> assertEquals(expected, response))
                .verifyComplete();

        var actual = trackService.findById("54428397").block();
        assertEquals(expected, actual);
    }

    @Test
    void whenFindTrackIntegrationReturnTrackserviceResponse() {
        var expected = trackServiceExpectedStub();
        var trackEntity = trackEntityStub();
        var integrationResponse = trackIntegrationStub();

        when(trackIntegration.findTrack("54428397"))
                .thenReturn(Mono.just(integrationResponse));
        when(trackRepository.save(trackEntity))
                .thenReturn(Mono.just(trackEntity));
        StepVerifier.create(trackService.findTrackIntegration("54428397"))
                .assertNext(response -> assertEquals(expected, response))
                .verifyComplete();

        var actual = trackService.findTrackIntegration("54428397").block();
        assertEquals(expected, actual);
    }
}