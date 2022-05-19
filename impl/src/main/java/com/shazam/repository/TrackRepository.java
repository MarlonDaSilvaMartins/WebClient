package com.shazam.repository;

import com.shazam.repository.entity.TrackEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends ReactiveMongoRepository<TrackEntity,String> {
}
