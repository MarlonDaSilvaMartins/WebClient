package com.shazam.controller.v1.track.mapper.response;

import com.shazam.controller.v1.track.model.response.TrackControllerResponse;
import com.shazam.track.model.response.TrackServiceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrackServiceResponseMapper {
    TrackControllerResponse toTrackServiceResponse(TrackServiceResponse trackServiceResponse);
}
