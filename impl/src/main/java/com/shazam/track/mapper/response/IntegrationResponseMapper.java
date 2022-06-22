package com.shazam.track.mapper.response;

import com.shazam.track.model.response.TrackIntegrationResponse;
import com.shazam.track.model.response.TrackServiceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")

public interface IntegrationResponseMapper {
    IntegrationResponseMapper MAPPER = Mappers.getMapper(IntegrationResponseMapper.class);

    TrackServiceResponse toTrackResponseMapper(TrackIntegrationResponse trackServiceResponse);
}
