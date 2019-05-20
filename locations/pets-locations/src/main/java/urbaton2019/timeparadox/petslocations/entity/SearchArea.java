package urbaton2019.timeparadox.petslocations.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class SearchArea {

    private static final String type = "MultiPolygon";

    private List<Zone> coordinates = new ArrayList<>();

    public void addZone(Zone zone) {
        coordinates.add(zone);
    }

}
