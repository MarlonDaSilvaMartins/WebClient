package com.shazam.controller.v1.track.model.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TrackControllerResponse {
    private String id;
    private String type;
    private String title;
    private String url;
    private String subtitle;
}
