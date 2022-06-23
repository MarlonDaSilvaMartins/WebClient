package com.shazam.controller.v1.track;

import com.shazam.controller.v1.track.model.response.TrackControllerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/track")
@Api(value = "Track")
public class TrackController {
    TrackControllerFacade trackControllerFacade;

    @ApiOperation(value = "find track")
    @GetMapping("/{trackId}")
    public Mono<TrackControllerResponse> findTrack(@PathVariable String trackId){
        return trackControllerFacade.findTrack(trackId);
    }

    @ApiOperation(value = "delete track")
    @DeleteMapping("/{trackId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteTrack(@PathVariable String trackId){
        return trackControllerFacade.deleteTrack(trackId);
    }

    @ApiOperation(value = "test kafka with callback")
    @GetMapping("/kafkaWithCallback")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> sendMessageWithCallback(){
        return trackControllerFacade.sendMessageWithCallback();
    }

    @ApiOperation(value = "find track and send with kafka")
    @GetMapping("/kafka")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> sendMessage(@RequestParam("trackId") String trackId){
        return trackControllerFacade.sendMessage(trackId);
    }
}