package com.shazam.track.mapper.response;

import com.shazam.repository.entity.TrackEntity;
import com.shazam.track.model.response.TrackServiceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TrackEntityMapper {
    TrackEntityMapper MAPPER = Mappers.getMapper(TrackEntityMapper.class);

    @Mapping(target = "link", source = "url")
    TrackServiceResponse toTrackServiceResponse(TrackEntity trackEntity);
}
