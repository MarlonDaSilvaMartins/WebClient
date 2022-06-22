package com.shazam.track;

import com.shazam.repository.TrackRepository;
import com.shazam.track.mapper.response.TrackEntityMapper;
import com.shazam.track.mapper.response.TrackIntegrationResponseMapper;
import com.shazam.track.model.response.TrackServiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class TrackService {
    private final TrackIntegration trackIntegration;
    private final TrackRepository trackRepository;
    private final KafkaTemplate<String, TrackServiceResponse> kafkaTemplate;

    public Mono<TrackServiceResponse> findById(String trackId){
        return trackRepository.findById(trackId)
                .map(TrackEntityMapper.MAPPER::toTrackServiceResponse);
    }

    public Mono<TrackServiceResponse> findTrackIntegration(String trackId){
        return trackIntegration.findTrack(trackId)
                .map(TrackIntegrationResponseMapper.MAPPER::toTrackEntity)
                .flatMap(trackRepository::save)
                .map(TrackEntityMapper.MAPPER::toTrackServiceResponse);
    }

    public Mono<Void> deleteTrack(String trackId){
        return trackRepository.deleteById(trackId);
    }

//    public void sendMessage(String msg){
//        String topicName = "teste";
//        kafkaTemplate.send(topicName, msg);
//    }

    public void sendMessage(){
        var trackServiceResponse = new TrackServiceResponse.Builder()
                .id("54428397")
                .link("https://www.shazam.com/track/54428397/without-me")
                .subtitle("Eminem")
                .type("MUSIC")
                .title("Without Me")
                .build();

        ListenableFuture<SendResult<String, TrackServiceResponse>> future = kafkaTemplate
                .send("teste", trackServiceResponse);

        future.addCallback(new ListenableFutureCallback<SendResult<String, TrackServiceResponse>>() {
            @Override
            public void onSuccess(SendResult<String, TrackServiceResponse> result) {
                System.out.println("Sent message=["+trackServiceResponse+"] with offset=["
                        + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + trackServiceResponse + "] due to : " + ex.getMessage());
            }
        });
    }
}
