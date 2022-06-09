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

    private TrackServiceResponse(Builder builder){
        this.id = builder.id;
        this.type = builder.type;
        this.title = builder.title;
        this.url = builder.url;
        this.subtitle = builder.subtitle;
    }

    public static class Builder{
        private String id;
        private String type;
        private String title;
        private String url;
        private String subtitle;

        public Builder(){
        }

        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder type(String type){
            this.type = type;
            return this;
        }

        public Builder title(String title){
            this.title = title;
            return this;
        }

        public Builder url(String url){
            this.url = url;
            return this;
        }

        public Builder subtitle(String subtitle){
            this.subtitle = subtitle;
            return this;
        }

        public TrackServiceResponse build(){
            return new TrackServiceResponse(this);
        }
    }
}
