package com.shazam.controller.v1.track;

import com.shazam.track.TrackServiceFacade;
import com.shazam.controller.v1.track.mapper.response.TrackServiceResponseMapper;
import com.shazam.controller.v1.track.model.response.TrackControllerResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class TrackControllerFacade {
    private final TrackServiceFacade trackServiceFacade;

    private final TrackServiceResponseMapper trackServiceResponseMapper;

    public Mono<TrackControllerResponse> findTrack(String trackId){
        return trackServiceFacade.findTrack(trackId)
                .map(trackServiceResponseMapper::toTrackServiceResponse);
    }

    public Mono<Void> deleteTrack(String trackId){
        return trackServiceFacade.deleteTrack(trackId);
    }
}
