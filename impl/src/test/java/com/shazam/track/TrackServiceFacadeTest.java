package com.shazam.track;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.shazam.track.serviceStub.TrackServiceFacadeStub.trackServiceExpectedStub;
import static com.shazam.track.serviceStub.TrackServiceFacadeStub.trackServiceStub;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TrackServiceFacade.class, TrackService.class})
class TrackServiceFacadeTest {
    @InjectMocks
    TrackServiceFacade trackServiceFacade;

    @Mock
    TrackService trackService;

    @Test
    void whenFindTrackByIdReturnTrackServiceResponse() {
        var expected = trackServiceExpectedStub();
        var serviceResponseStub = trackServiceStub();

        when(trackService.findById("54428397"))
                .thenReturn(Mono.just(serviceResponseStub));
        StepVerifier.create(trackServiceFacade.findTrack("54428397"))
                .assertNext(response -> assertEquals(expected, response))
                .verifyComplete();

        var actual = trackServiceFacade.findTrack("54428397").block();
        assertEquals(expected, actual);
    }

    @Test
    void whenFindTrackIntegrationReturnTrackServiceResponse() {
        var expected = trackServiceExpectedStub();
        var serviceResponseStub = trackServiceStub();

        when(trackService.findById("54428397"))
                .thenReturn(Mono.empty());
        when(trackService.findTrackIntegration("54428397"))
                .thenReturn(Mono.just(serviceResponseStub));
        StepVerifier.create(trackServiceFacade.findTrack("54428397"))
                .assertNext(response -> assertEquals(expected, response))
                .verifyComplete();

        var actual = trackServiceFacade.findTrack("54428397").block();
        assertEquals(expected, actual);
    }
}