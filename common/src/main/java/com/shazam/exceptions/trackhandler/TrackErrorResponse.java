package com.shazam.exceptions.trackhandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TrackErrorResponse {
    private ArrayList<TrackErrorDetail> detail;
}
