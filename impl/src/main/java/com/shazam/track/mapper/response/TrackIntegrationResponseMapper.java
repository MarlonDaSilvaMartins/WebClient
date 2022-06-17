package com.shazam.track.mapper.response;

import com.shazam.repository.entity.TrackEntity;
import com.shazam.track.model.response.TrackIntegrationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrackIntegrationResponseMapper {
    TrackEntity toTrackEntity(TrackIntegrationResponse trackIntegrationResponse);
}
