package com.shazam.track.mapper.response;

import com.shazam.repository.entity.TrackEntity;
import com.shazam.track.model.response.TrackServiceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrackEntityMapper {
    TrackServiceResponse toTrackServiceResponse(TrackEntity trackEntity);
}
