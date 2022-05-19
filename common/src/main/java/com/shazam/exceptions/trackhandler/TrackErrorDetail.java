package com.shazam.exceptions.trackhandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TrackErrorDetail {
    private List<String> loc;
    private String msg;
    private String type;
}
