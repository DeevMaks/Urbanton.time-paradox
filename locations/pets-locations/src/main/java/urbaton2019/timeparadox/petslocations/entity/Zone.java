package urbaton2019.timeparadox.petslocations.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Zone {

    @Getter @EqualsAndHashCode.Include private String id;
    @EqualsAndHashCode.Exclude private List<GeoPoint> polygon = new ArrayList<>();

    private Zone(String id) {
        this.id = id;
    }

    public List<GeoPoint> getPolygon() {
        return new ArrayList<>(polygon);
    }

    public void setPolygon(List<GeoPoint> polygon) {
        this.polygon = polygon;
    }

    public static Zone of(Zone zone) {
        Zone newZone = new Zone(UUID.randomUUID().toString());
        newZone.setPolygon(zone.getPolygon());
        return newZone;
    }

    public static Zone of(List<GeoPoint> polygon) {
        Zone newZone = new Zone(UUID.randomUUID().toString());
        newZone.setPolygon(polygon);
        return newZone;
    }
}
