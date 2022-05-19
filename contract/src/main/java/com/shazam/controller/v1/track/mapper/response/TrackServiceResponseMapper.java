package com.shazam.controller.v1.track.mapper.response;

import com.shazam.controller.v1.track.model.response.TrackControllerResponse;
import com.shazam.track.model.response.TrackServiceResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrackServiceResponseMapper {
    public static TrackControllerResponse toTrackServiceResponse(TrackServiceResponse trackServiceResponse) {
        return TrackControllerResponse.builder()
                .id(trackServiceResponse.getId())
                .url(trackServiceResponse.getUrl())
                .subtitle(trackServiceResponse.getSubtitle())
                .type(trackServiceResponse.getType())
                .title(trackServiceResponse.getTitle())
                .build();
    }
}
