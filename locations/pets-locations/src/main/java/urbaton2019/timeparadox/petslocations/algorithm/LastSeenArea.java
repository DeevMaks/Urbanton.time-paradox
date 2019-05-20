package urbaton2019.timeparadox.petslocations.algorithm;

import urbaton2019.timeparadox.petslocations.entity.GeoPoint;
import urbaton2019.timeparadox.petslocations.entity.SearchArea;
import urbaton2019.timeparadox.petslocations.entity.Zone;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LastSeenArea {

    public static SearchArea compute(Date lastSeenTime, GeoPoint lastSeenLocationPoint) {
        int k;
        Instant lastSeenInstant = lastSeenTime.toInstant();
        if (Instant.now().minus(1, ChronoUnit.DAYS).isAfter(lastSeenInstant)) {
            k = 1;
        } else {
            k = 2;
        }

        double latitude = lastSeenLocationPoint.getLatitude();
        double longitude = lastSeenLocationPoint.getLongitude();

        int radius = 1000 * k; // meters
        double[] displacements = computeDistance(latitude, radius); // in degrees (latitude displacement, longitude displacemnt)

        double computedPlusLatitude = latitude + displacements[0];
        double plusLatitude = computedPlusLatitude > 90 ? 90 : computedPlusLatitude;
        double computedMinusLatitude = latitude - displacements[0];
        double minusLatitude = computedMinusLatitude < -90 ? -90 : computedMinusLatitude;

        double computedPlusLongitude = longitude + displacements[1];
        double computedMinusLongitude = longitude - displacements[1];
        double plusLongitude = computedPlusLongitude > 180 ? 180 : computedPlusLongitude;
        double minusLongitude = computedMinusLongitude < -180 ? -180 : computedMinusLongitude;

        List<GeoPoint> points = new ArrayList<>();
        points.add(GeoPoint.of(minusLongitude, minusLatitude));
        points.add(GeoPoint.of(minusLongitude, plusLatitude));
        points.add(GeoPoint.of(plusLongitude, plusLatitude));
        points.add(GeoPoint.of(plusLongitude, minusLatitude));
        Zone zone = Zone.of(points);
        SearchArea searchArea = new SearchArea();
        searchArea.addZone(zone);

        return searchArea;
    }

    private static double[] computeDistance(double latitude, int radius) {
        double oneDegreeLatitude = Math.pow(10, 7) / 90; // meters

        int bearing = 0;
        double cosBearing = Math.cos(bearing);
        double sinBearing = Math.sin(bearing);
        double cosLatitude = Math.cos(latitude);
        double eastDisplacement = radius * sinBearing / cosLatitude / oneDegreeLatitude;
        double northDisplacement = radius * cosBearing / oneDegreeLatitude;

        return new double[]{eastDisplacement, northDisplacement};
    }
}
