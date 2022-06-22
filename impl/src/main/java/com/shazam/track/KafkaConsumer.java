package com.shazam.track;

import com.shazam.track.model.response.TrackServiceResponse;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "teste", groupId = "myGroup")
    public void listener(@Payload List<TrackServiceResponse> data){
        System.out.println(data);
    }
}
