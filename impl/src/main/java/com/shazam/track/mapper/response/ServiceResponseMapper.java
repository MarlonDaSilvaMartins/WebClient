package com.shazam.track.mapper.response;

import com.shazam.repository.entity.TrackEntity;
import com.shazam.track.model.response.TrackServiceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ServiceResponseMapper {
    ServiceResponseMapper MAPPER = Mappers.getMapper(ServiceResponseMapper.class);

    TrackEntity toTrackEntityMapper(TrackServiceResponse trackServiceResponse);
}
