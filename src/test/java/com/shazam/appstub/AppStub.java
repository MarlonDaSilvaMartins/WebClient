package com.shazam.appstub;

import com.shazam.controller.v1.track.model.response.TrackControllerResponse;
import com.shazam.repository.entity.TrackEntity;
import com.shazam.track.model.response.TrackIntegrationResponse;

public class AppStub {
    public static TrackControllerResponse trackControllerExpectedStub(){
        return TrackControllerResponse.builder()
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

    public static TrackIntegrationResponse trackIntegrationResponseStub(){
        return TrackIntegrationResponse.builder()
                .id("54428397")
                .url("https://www.shazam.com/track/54428397/without-me")
                .subtitle("Eminem")
                .type("MUSIC")
                .title("Without Me")
                .build();
    }
}
