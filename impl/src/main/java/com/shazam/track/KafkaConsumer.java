package com.shazam.track;

import com.shazam.track.model.response.TrackServiceResponse;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "teste", groupId = "myGroup")
    public void listener(TrackServiceResponse data){
        System.out.println("msg: " + data);
    }
}
