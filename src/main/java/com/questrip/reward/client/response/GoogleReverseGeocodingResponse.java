package com.questrip.reward.client.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GoogleReverseGeocodingResponse {
    List<Result> results;

    @Getter
    @NoArgsConstructor
    public static class Result {
        private String formatted_address;

        public Result(String formatted_address) {
            this.formatted_address = formatted_address;
        }
    }

    public static GoogleReverseGeocodingResponse error() {
        return new GoogleReverseGeocodingResponse(List.of(new Result("UNKNOWN")));
    }

    public GoogleReverseGeocodingResponse(List<Result> results) {
        this.results = results;
    }

    public String toAddress() {
        return results.get(0).formatted_address;
    }
}
