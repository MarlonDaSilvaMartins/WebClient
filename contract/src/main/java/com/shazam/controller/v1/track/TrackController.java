package com.shazam.controller.v1.track;

import com.shazam.controller.v1.track.model.response.TrackControllerResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/track")
//@Api("Track")
public class TrackController {
    TrackControllerFacade trackControllerFacade;

    @GetMapping("/{trackId}")
    public Mono<TrackControllerResponse> findTrack(@PathVariable String trackId){
        return trackControllerFacade.findTrack(trackId);
    }

    @DeleteMapping("/{trackId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteTrack(@PathVariable String trackId){
        return trackControllerFacade.deleteTrack(trackId);
    }
}
