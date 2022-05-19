package com.shazam.exceptions.trackhandler;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TrackErrorException extends RuntimeException{
    private final TrackErrorResponse trackErrorResponse;

    public TrackErrorException(TrackErrorResponse trackErrorResponse) {
        super();
        this.trackErrorResponse = trackErrorResponse;
    }
}
