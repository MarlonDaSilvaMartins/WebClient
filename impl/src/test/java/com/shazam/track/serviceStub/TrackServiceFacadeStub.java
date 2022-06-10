package com.shazam.track.serviceStub;

import com.shazam.track.model.response.TrackServiceResponse;

public class TrackServiceFacadeStub {
    public static TrackServiceResponse trackServiceExpectedStub(){
        return new TrackServiceResponse.Builder()
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
