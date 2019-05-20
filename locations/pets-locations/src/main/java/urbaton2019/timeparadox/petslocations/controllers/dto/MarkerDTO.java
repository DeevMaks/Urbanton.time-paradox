package urbaton2019.timeparadox.petslocations.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MarkerDTO {

    private long petId;
    private long timeStamp;
    private double lastSeenLongitude;
    private double lastSeenLatitude;
    private double homeLongitude;
    private double homeLatitude;

}
