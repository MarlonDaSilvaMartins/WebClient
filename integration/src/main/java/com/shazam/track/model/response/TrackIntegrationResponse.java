package com.shazam.track.model.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TrackIntegrationResponse {
    @JsonProperty("key")
    private String id;
    private String type;
    private String title;
    private String url;
    private String subtitle;
}
