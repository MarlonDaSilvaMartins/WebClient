package com.shazam.track.serviceStub;

import com.shazam.track.model.response.TrackServiceResponse;

public class TrackServiceFacadeStub {
    public static TrackServiceResponse trackServiceExpectedStub(){
        return TrackServiceResponse.builder()
                .id("54428397")
                .url("https://www.shazam.com/track/54428397/without-me")
                .subtitle("Eminem")
                .type("MUSIC")
                .title("Without Me")
                .build();
    }

    public static TrackServiceResponse trackServiceStub(){
        return TrackServiceResponse.builder()
                .id("54428397")
                .url("https://www.shazam.com/track/54428397/without-me")
                .subtitle("Eminem")
                .type("MUSIC")
                .title("Without Me")
                .build();
    }

    public static TrackServiceResponse trackServiceNullStub(){
        return TrackServiceResponse.builder()
                .id(null)
                .url(null)
                .subtitle(null)
                .type(null)
                .title(null)
                .build();
    }
}
