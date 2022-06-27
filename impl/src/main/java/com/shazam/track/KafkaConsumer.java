package com.shazam.track;

import com.shazam.repository.entity.TrackEntity;
import com.shazam.track.model.response.TrackServiceResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class KafkaConsumer {
    private final TrackService trackService;

    @KafkaListener(topics = "teste", groupId = "myGroup")
    public Flux<TrackEntity> listener(@Payload List<TrackServiceResponse> data){
        log.info("RECEBENDO DA INTEGRAÇÃO: "+ data);
        return trackService.save(data);
    }
}
