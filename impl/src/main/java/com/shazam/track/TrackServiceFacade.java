package com.shazam.track;

import com.shazam.track.model.response.TrackServiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class TrackServiceFacade {
    private final TrackService trackService;
    private final KafkaTemplate<String, TrackServiceResponse> kafkaTemplate;

    public Mono<TrackServiceResponse> findTrack(String trackId){
        return trackService.findById(trackId)
                .switchIfEmpty(Mono.defer(() -> trackService.findTrackIntegration(trackId)));
    }

    public Mono<Void> deleteTrack(String trackId){
        return trackService.deleteTrack(trackId);
    }

    public Mono<Void> sendMessageWithCallback(){
        return trackService.sendMessageWithCallback();
    }

    public Mono<Void> sendMessage(String trackId) {
        return trackService.sendMessage(trackId);
    }
}
