package com.shazam.controller.v1.track.controllerStub;

import com.shazam.controller.v1.track.model.response.TrackControllerResponse;
import com.shazam.track.model.response.TrackServiceResponse;

public class TrackControllerFacadeStub {
    public static TrackControllerResponse trackControllerExpectedStub(){
        return TrackControllerResponse.builder()
                .id("54428397")
                .url("https://www.shazam.com/track/54428397/without-me")
                .subtitle("Eminem")
                .type("MUSIC")
                .title("Without Me")
                .build();
    }

    public static TrackServiceResponse trackServiceStub(){
        return new TrackServiceResponse.Builder()
                .id("54428397")
                .url("https://www.shazam.com/track/54428397/without-me")
                .subtitle("Eminem")
                .type("MUSIC")
                .title("Without Me")
                .build();
    }
}
