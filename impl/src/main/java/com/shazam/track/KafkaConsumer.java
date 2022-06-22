package com.shazam.track;

import com.shazam.repository.TrackRepository;
import com.shazam.track.mapper.response.ServiceResponseMapper;
import com.shazam.track.model.response.TrackServiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@AllArgsConstructor
public class KafkaConsumer {
    private final TrackRepository trackRepository;

    @KafkaListener(topics = "teste", groupId = "myGroup")
    public void listener(@Payload List<TrackServiceResponse> data){
        var response = data;
        System.out.println(data);
    }

    @KafkaListener(topics = "teste", groupId = "myGroup")
    public void listener2(@Payload List<TrackServiceResponse> data){
        System.out.println(data);
        Mono.just(data).flatMapMany(trackServiceResponses -> Flux.fromStream(trackServiceResponses.stream()))
                .map(ServiceResponseMapper.MAPPER::toTrackEntityMapper)
                .flatMap(trackRepository::save);
    }
}
