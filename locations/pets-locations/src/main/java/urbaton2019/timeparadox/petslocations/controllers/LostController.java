package urbaton2019.timeparadox.petslocations.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import urbaton2019.timeparadox.petslocations.controllers.dto.MarkerDTO;
import urbaton2019.timeparadox.petslocations.controllers.dto.MessageDTO;
import urbaton2019.timeparadox.petslocations.entity.GeoPoint;
import urbaton2019.timeparadox.petslocations.entity.SearchLocation;
import urbaton2019.timeparadox.petslocations.service.SearchLocationService;

import javax.annotation.Resource;
import java.util.Date;

@Controller
public class LostController {

    @Resource
    private SearchLocationService searchLocationService;

    @PostMapping("/lost-update")
    public ResponseEntity<MessageDTO> updateSearchLocation(@RequestBody MarkerDTO markerDTO) {
        GeoPoint lastSeenPoint = GeoPoint.of(markerDTO.getLastSeenLongitude(), markerDTO.getLastSeenLatitude());
        GeoPoint homePoint = GeoPoint.of(markerDTO.getHomeLongitude(), markerDTO.getHomeLatitude());
        Date lastSeenDate = new Date(markerDTO.getTimeStamp());
        SearchLocation searchLocation = SearchLocation.of(lastSeenDate, lastSeenPoint, homePoint);

        SearchLocation savedLocation = searchLocationService.save(searchLocation.withId(markerDTO.getPetId()));

        MessageDTO messageDTO = new MessageDTO(savedLocation.getPetId(), true, "");

        return ResponseEntity.ok(messageDTO);
    }
}
