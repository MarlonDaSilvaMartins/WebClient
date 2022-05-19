package com.shazam.track.mapper.response;

import com.shazam.repository.entity.TrackEntity;
import com.shazam.track.model.response.TrackServiceResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrackEntityMapper {
    public static TrackServiceResponse toTrackServiceResponse(TrackEntity trackEntity) {
        return TrackServiceResponse.builder()
                .id(trackEntity.getId())
                .url(trackEntity.getUrl())
                .subtitle(trackEntity.getSubtitle())
                .type(trackEntity.getType())
                .title(trackEntity.getTitle())
                .build();
    }
}
