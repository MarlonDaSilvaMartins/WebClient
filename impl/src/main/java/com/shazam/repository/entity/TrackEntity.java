package com.shazam.repository.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "Track")
public class TrackEntity {
    @Id
    private String id;
    private String type;
    private String title;
    private String url;
    private String subtitle;
}
