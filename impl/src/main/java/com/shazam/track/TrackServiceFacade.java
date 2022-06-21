package com.shazam.track;

import com.shazam.track.model.response.TrackServiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class TrackServiceFacade {
    private final TrackService trackService;

    public Mono<TrackServiceResponse> findTrack(String trackId){
        return trackService.findById(trackId)
                .switchIfEmpty(Mono.defer(() -> trackService.findTrackIntegration(trackId)));
    }

    public Mono<Void> deleteTrack(String trackId){
        return trackService.deleteTrack(trackId);
    }

//    public void sendMessage(String msg){
//        trackService.sendMessage(msg);
//    }
    public void sendMessage(){
        trackService.sendMessage();
    }
}
