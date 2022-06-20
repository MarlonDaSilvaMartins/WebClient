package com.shazam.track.mapper.response;

import com.shazam.repository.entity.TrackEntity;
import com.shazam.track.model.response.TrackIntegrationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TrackIntegrationResponseMapper {
    TrackIntegrationResponseMapper MAPPER = Mappers.getMapper(TrackIntegrationResponseMapper.class);

    TrackEntity toTrackEntity(TrackIntegrationResponse trackIntegrationResponse);
}
