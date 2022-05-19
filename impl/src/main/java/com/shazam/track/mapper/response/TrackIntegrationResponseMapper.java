package com.shazam.track.mapper.response;

import com.shazam.repository.entity.TrackEntity;
import com.shazam.track.model.response.TrackIntegrationResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrackIntegrationResponseMapper {
    public static TrackEntity toTrackEntity(TrackIntegrationResponse trackIntegrationResponse) {
        return TrackEntity.builder()
                .id(trackIntegrationResponse.getId())
                .url(trackIntegrationResponse.getUrl())
                .subtitle(trackIntegrationResponse.getSubtitle())
                .type(trackIntegrationResponse.getType())
                .title(trackIntegrationResponse.getTitle())
                .build();
    }
}
