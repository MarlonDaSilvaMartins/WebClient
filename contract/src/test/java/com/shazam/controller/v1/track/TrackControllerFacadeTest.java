//package com.shazam.controller.v1.track;
//
//import com.shazam.controller.v1.track.model.response.TrackControllerResponse;
//import com.shazam.track.TrackServiceFacade;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.test.context.ContextConfiguration;
//import reactor.core.publisher.Mono;
//import reactor.test.StepVerifier;
//
//import static com.shazam.controller.v1.track.controllerStub.TrackControllerFacadeStub.trackControllerExpectedStub;
//import static com.shazam.controller.v1.track.controllerStub.TrackControllerFacadeStub.trackServiceStub;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@ContextConfiguration(classes = {TrackControllerFacade.class, TrackServiceFacade.class})
//class TrackControllerFacadeTest {
//    @InjectMocks
//    TrackControllerFacade trackControllerFacade;
//
//    @Mock
//    TrackServiceFacade trackServiceFacade;
//
//    @Test
//    void whenFindTrackReturnTrackControllerResponse() {
//        var expected = trackControllerExpectedStub();
//        var serviceResponseStub = trackServiceStub();
//
//        when(trackServiceFacade.findTrack("54428397"))
//                .thenReturn(Mono.just(serviceResponseStub));
//        StepVerifier.create(trackControllerFacade.findTrack("54428397"))
//                .assertNext((TrackControllerResponse response) -> assertEquals(expected, response))
//                .verifyComplete();
//
//        var actual = trackControllerFacade.findTrack("54428397").block();
//        assertEquals(expected, actual);
//    }
//}