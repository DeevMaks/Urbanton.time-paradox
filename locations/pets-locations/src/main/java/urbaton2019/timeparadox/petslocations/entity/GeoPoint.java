package urbaton2019.timeparadox.petslocations.entity;

import lombok.Value;

@Value
public class GeoPoint {

    private static final String type = "Point";
    private final double longitude;
    private final double latitude;


    private GeoPoint(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static GeoPoint of(double longitude, double latitude) {
        return new GeoPoint(longitude, latitude);
    }
}
