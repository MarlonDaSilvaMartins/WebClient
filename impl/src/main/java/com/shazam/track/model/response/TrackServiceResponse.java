package com.shazam.track.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrackServiceResponse {
    private String id;
    private String type;
    private String title;
    private String url;
    private String subtitle;

    private TrackServiceResponse(builder builder){
        this.id = builder.id;
        this.type = builder.type;
        this.title = builder.title;
        this.url = builder.url;
        this.subtitle = builder.subtitle;
    }

    public static class builder{
        private String id;
        private String type;
        private String title;
        private String url;
        private String subtitle;

        public builder(){
        }

        public builder id(String id){
            this.id = id;
            return this;
        }

        public builder type(String type){
            this.type = type;
            return this;
        }

        public builder title(String title){
            this.title = title;
            return this;
        }

        public builder url(String url){
            this.url = url;
            return this;
        }

        public builder subtitle(String subtitle){
            this.subtitle = subtitle;
            return this;
        }

        public TrackServiceResponse build(){
            return new TrackServiceResponse(this);
        }
    }
}
