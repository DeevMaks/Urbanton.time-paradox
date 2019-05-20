package urbaton2019.timeparadox.petslocations.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import urbaton2019.timeparadox.petslocations.algorithm.HomeArea;
import urbaton2019.timeparadox.petslocations.algorithm.LastSeenArea;

import java.util.Date;

@Data
public class SearchLocation {

    @Id
    private final Long petId;
    @EqualsAndHashCode.Exclude private Date lastSeenTime;
    @EqualsAndHashCode.Exclude private GeoPoint lastSeenLocationPoint;
    @EqualsAndHashCode.Exclude private GeoPoint homeLocationPoint;
    @EqualsAndHashCode.Exclude private SearchArea lastSeenSearchArea;
    @EqualsAndHashCode.Exclude private SearchArea homeArea;


    private SearchLocation(Long petId, Date lastSeenTime, GeoPoint lastSeenLocationPoint, GeoPoint homeLocationPoint,
                           SearchArea lastSeenSearchArea, SearchArea homeArea) {
        this.petId = petId;
        this.lastSeenTime = lastSeenTime;
        this.lastSeenLocationPoint = lastSeenLocationPoint;
        this.homeLocationPoint = homeLocationPoint;
        this.lastSeenSearchArea = lastSeenSearchArea;
        this.homeArea = homeArea;
    }

    public SearchLocation withId(Long petId) {
        return new SearchLocation(petId, this.lastSeenTime, this.lastSeenLocationPoint, this.homeLocationPoint,
                this.lastSeenSearchArea, this.homeArea);
    }

    public static SearchLocation of(Date lastSeenTime, GeoPoint lastSeenLocationPoint, GeoPoint homeLocationPoint) {
        SearchArea lastSeenSearchArea = LastSeenArea.compute(lastSeenTime, lastSeenLocationPoint);
        SearchArea homeSearchArea = HomeArea.compute(homeLocationPoint);
        return new SearchLocation(null, lastSeenTime, lastSeenLocationPoint, homeLocationPoint,
                lastSeenSearchArea, homeSearchArea);
    }
}
