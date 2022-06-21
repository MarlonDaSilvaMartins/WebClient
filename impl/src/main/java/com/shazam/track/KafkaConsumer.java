package com.shazam.track;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "teste", groupId = "myGroup")
    public void listener(String data){
        System.out.println(data);
    }
}
