package com.questrip.reward.utils;

import lombok.experimental.UtilityClass;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

@UtilityClass
public class GeometryUtils {

    public static final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    public static Point getPoint(Double longitude, Double latitude) {
        return geometryFactory.createPoint(new Coordinate(longitude, latitude));
    }
}