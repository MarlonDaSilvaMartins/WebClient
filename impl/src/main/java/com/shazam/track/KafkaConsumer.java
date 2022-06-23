package com.shazam.track;

import com.shazam.repository.TrackRepository;
import com.shazam.track.mapper.response.ServiceResponseMapper;
import com.shazam.track.model.response.TrackServiceResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class KafkaConsumer {
    private final TrackRepository trackRepository;

    @KafkaListener(topics = "teste", groupId = "myGroup")
    public Mono<Void> listener(@Payload List<TrackServiceResponse> data){
        log.info("RECEBENDO DA INTEGRAÇÃO: "+ data);
        return Mono.just(data).flatMapMany(trackServiceResponses -> Flux.fromStream(trackServiceResponses.stream()))
                .map(ServiceResponseMapper.MAPPER::toTrackEntityMapper)
                .flatMap(trackRepository::save)
                .then(Mono.empty());
    }
}
