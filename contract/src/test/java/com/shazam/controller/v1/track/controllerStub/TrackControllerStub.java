package com.shazam.controller.v1.track.controllerStub;

import com.shazam.controller.v1.track.model.response.TrackControllerResponse;

public class TrackControllerStub {
    public static TrackControllerResponse trackControllerExpectedStub(){
        return TrackControllerResponse.builder()
                .id("54428397")
                .url("https://www.shazam.com/track/54428397/without-me")
                .subtitle("Eminem")
                .type("MUSIC")
                .title("Without Me")
                .build();
    }

    public static TrackControllerResponse trackControllerStub(){
        return TrackControllerResponse.builder()
                .id("54428397")
                .url("https://www.shazam.com/track/54428397/without-me")
                .subtitle("Eminem")
                .type("MUSIC")
                .title("Without Me")
                .build();
    }
}
