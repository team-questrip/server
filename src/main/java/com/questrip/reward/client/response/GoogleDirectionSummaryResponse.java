package com.questrip.reward.client.response;

import com.questrip.reward.domain.direction.DirectionSummary;
import lombok.Getter;

import java.util.List;

@Getter
public class GoogleDirectionSummaryResponse {
    private List<Route> routes;

    @Getter
    public static class Route {
        private List<Leg> legs;
    }

    @Getter
    public static class Leg {
        private TimeZoneTextValue distance;
        private TimeZoneTextValue duration;
    }

    @Getter
    public static class TimeZoneTextValue {
        private String text;
        private Long value;
    }

    public DirectionSummary toSummary() {
        return new DirectionSummary(routes.get(0).getLegs().get(0).distance.getText(), routes.get(0).getLegs().get(0).duration.getText());
    }
}
