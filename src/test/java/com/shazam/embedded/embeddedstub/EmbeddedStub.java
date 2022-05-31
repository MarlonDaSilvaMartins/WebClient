package com.shazam.embedded.embeddedstub;

import com.shazam.repository.entity.TrackEntity;
import com.shazam.track.model.response.TrackServiceResponse;

public class EmbeddedStub {
    public static TrackEntity trackEntityStub(){
        return TrackEntity.builder()
                .id("54428397")
                .url("https://www.shazam.com/track/54428397/without-me")
                .subtitle("Eminem")
                .type("MUSIC")
                .title("Without Me")
                .build();
    }
}
