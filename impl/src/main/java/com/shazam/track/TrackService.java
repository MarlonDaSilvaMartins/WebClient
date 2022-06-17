package com.shazam.track;

import com.shazam.repository.TrackRepository;
import com.shazam.track.mapper.response.TrackEntityMapper;
import com.shazam.track.mapper.response.TrackIntegrationResponseMapper;
import com.shazam.track.model.response.TrackServiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class TrackService {
    private final TrackIntegration trackIntegration;
    private final TrackRepository trackRepository;

    private TrackEntityMapper trackEntityMapper;
    private TrackIntegrationResponseMapper trackIntegrationResponseMapper;

    public Mono<TrackServiceResponse> findById(String trackId){
        return trackRepository.findById(trackId).map(trackEntityMapper::toTrackServiceResponse);
    }

    public Mono<TrackServiceResponse> findTrackIntegration(String trackId){
        return trackIntegration.findTrack(trackId)
                .map(trackIntegrationResponseMapper::toTrackEntity)
                .flatMap(trackRepository::save)
                .map(trackEntityMapper::toTrackServiceResponse);
    }

    public Mono<Void> deleteTrack(String trackId){
        return trackRepository.deleteById(trackId);
    }
}
