package com.questrip.reward.api.v1.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReverseGeocodeResponse {
    private String formattedAddress;

    public ReverseGeocodeResponse(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
}
