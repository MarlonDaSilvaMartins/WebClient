package com.shazam.controller.v1.track.mapper.response;

import com.shazam.controller.v1.track.model.response.TrackControllerResponse;
import com.shazam.track.model.response.TrackServiceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TrackServiceResponseMapper {
    TrackServiceResponseMapper MAPPER = Mappers.getMapper(TrackServiceResponseMapper.class);

    @Mapping(target = "url", source = "link")
    TrackControllerResponse toTrackServiceResponse(TrackServiceResponse trackServiceResponse);
}
