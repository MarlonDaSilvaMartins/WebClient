package com.shazam.controller.v1.track;

import com.shazam.controller.v1.track.mapper.response.TrackServiceResponseMapper;
import com.shazam.controller.v1.track.model.response.TrackControllerResponse;
import com.shazam.track.TrackServiceFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class TrackControllerFacade {
    private final TrackServiceFacade trackServiceFacade;

    public Mono<TrackControllerResponse> findTrack(String trackId){
        return trackServiceFacade.findTrack(trackId)
                .map(TrackServiceResponseMapper.MAPPER::toTrackServiceResponse);
    }

    public Mono<Void> deleteTrack(String trackId){
        return trackServiceFacade.deleteTrack(trackId);
    }

    public void sendMessageWithCallback(){
        trackServiceFacade.sendMessageWithCallback();
    }

    public void sendMessage(String trackId) {
        trackServiceFacade.sendMessage(trackId);
    }
}
