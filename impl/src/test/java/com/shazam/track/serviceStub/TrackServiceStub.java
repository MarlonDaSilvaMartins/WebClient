package com.shazam.track.serviceStub;

import com.shazam.repository.entity.TrackEntity;
import com.shazam.track.model.response.TrackIntegrationResponse;
import com.shazam.track.model.response.TrackServiceResponse;

public class TrackServiceStub {
    public static TrackServiceResponse trackServiceExpectedStub(){
        return TrackServiceResponse.builder()
                .id("54428397")
                .url("https://www.shazam.com/track/54428397/without-me")
                .subtitle("Eminem")
                .type("MUSIC")
                .title("Without Me")
                .build();
    }

    public static TrackIntegrationResponse trackIntegrationStub(){
        return TrackIntegrationResponse.builder()
                .id("54428397")
                .url("https://www.shazam.com/track/54428397/without-me")
                .subtitle("Eminem")
                .type("MUSIC")
                .title("Without Me")
                .build();
    }

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
