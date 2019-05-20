package urbaton2019.timeparadox.petslocations.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import urbaton2019.timeparadox.petslocations.controllers.dto.MarkerDTO;
import urbaton2019.timeparadox.petslocations.controllers.dto.SearchAreaDTO;
import urbaton2019.timeparadox.petslocations.entity.GeoPoint;
import urbaton2019.timeparadox.petslocations.entity.Zone;
import urbaton2019.timeparadox.petslocations.service.SearchLocationService;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class SearchController {

    @Resource
    private SearchLocationService searchLocationService;

    @GetMapping("/marker/{petId}")
    public ResponseEntity<MarkerDTO> getMarker(@PathVariable long petId) {
        final MarkerDTO[] markerDTO = new MarkerDTO[1];
        searchLocationService.getByPetId(petId).ifPresent(searchLocation -> {

            long timestamp = searchLocation.getLastSeenTime().toInstant().toEpochMilli();

            GeoPoint lastSeenCoordinates = searchLocation.getLastSeenLocationPoint();
            double lastSeenLongitude = lastSeenCoordinates.getLongitude();
            double lastSeenLatitude = lastSeenCoordinates.getLatitude();

            GeoPoint homeCoordinates = searchLocation.getHomeLocationPoint();
            double homeLongitude = homeCoordinates.getLongitude();
            double homeLatitude = homeCoordinates.getLatitude();

            markerDTO[0] = new MarkerDTO(searchLocation.getPetId(), timestamp, lastSeenLongitude, lastSeenLatitude,
                    homeLongitude, homeLatitude);
        });
        if (markerDTO[0] == null) {
            return ResponseEntity.badRequest().body(new MarkerDTO(petId, 0,0,0,0,0));
        }
        return ResponseEntity.ok(markerDTO[0]);
    }

    @GetMapping("/last-seen-area/{petId}")
    public ResponseEntity<SearchAreaDTO> getLastSeenSearchArea(@PathVariable long petId) {
        final SearchAreaDTO[] searchAreaDTO = new SearchAreaDTO[1];

        searchLocationService.getByPetId(petId).ifPresent(searchLocation -> {

        searchAreaDTO[0] = new SearchAreaDTO(searchLocation.getPetId());

        List<Zone> searchAreaCoordinates = searchLocation.getLastSeenSearchArea().getCoordinates();

        double[][][] searchCoordinates = extractCoordinates(searchAreaCoordinates);

        searchAreaDTO[0].setSearchAreaCoordinates(searchCoordinates);
        });

        if (searchAreaDTO[0] == null) {
            return ResponseEntity.badRequest().body(new SearchAreaDTO(petId));
        }

        return ResponseEntity.ok(searchAreaDTO[0]);
    }

    @GetMapping("/home-area/{petId}")
    public ResponseEntity<SearchAreaDTO> getHomeSearchArea(@PathVariable long petId) {
        final SearchAreaDTO[] searchAreaDTO = new SearchAreaDTO[1];

        searchLocationService.getByPetId(petId).ifPresent(searchLocation -> {

            searchAreaDTO[0] = new SearchAreaDTO(searchLocation.getPetId());

            List<Zone> homeAreaCoordinates = searchLocation.getHomeArea().getCoordinates();

            double[][][] searchCoordinates = extractCoordinates(homeAreaCoordinates);

            searchAreaDTO[0].setSearchAreaCoordinates(searchCoordinates);
        });

        if (searchAreaDTO[0] == null) {
            return ResponseEntity.badRequest().body(new SearchAreaDTO(petId));
        }

        return ResponseEntity.ok(searchAreaDTO[0]);
    }

    private double[][][] extractCoordinates(List<Zone> searchAreaCoordinates) {
        double[][][] searchCoordinates = new double[searchAreaCoordinates.size()][][];

        for (int i = 0; i < searchAreaCoordinates.size(); i++) {
            Zone zone = searchAreaCoordinates.get(i);
            searchCoordinates[i] = new double[zone.getPolygon().size()][];
            for (int j = 0; j < zone.getPolygon().size(); j++) {
                GeoPoint point = zone.getPolygon().get(j);
                double[] coordinates = {point.getLatitude(), point.getLongitude()};
                searchCoordinates[i][j] = coordinates;
            }
        }
        return searchCoordinates;
    }

}
