package com.shazam.track;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shazam.repository.TrackRepository;
import com.shazam.track.mapper.response.TrackEntityMapper;
import com.shazam.track.mapper.response.TrackIntegrationResponseMapper;
import com.shazam.track.model.response.TrackServiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
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
        kafkaTemplate.send("teste", trackServiceResponse);
    }
}
