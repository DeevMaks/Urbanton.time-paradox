package urbaton2019.timeparadox.petslocations.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import urbaton2019.timeparadox.petslocations.entity.SearchLocation;

@Repository
public interface SearchLocationRepository extends MongoRepository<SearchLocation, Long> {

}
