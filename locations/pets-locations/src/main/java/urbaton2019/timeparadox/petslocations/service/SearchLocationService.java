package urbaton2019.timeparadox.petslocations.service;

import org.springframework.stereotype.Service;
import urbaton2019.timeparadox.petslocations.entity.SearchLocation;
import urbaton2019.timeparadox.petslocations.repository.SearchLocationRepository;

import java.util.Optional;

@Service
public class SearchLocationService {

    private SearchLocationRepository repository;

    public SearchLocationService(SearchLocationRepository repository) {
        this.repository = repository;
    }

    public SearchLocation save(SearchLocation searchLocation) {
        return repository.save(searchLocation);
    }

    public Optional<SearchLocation> getByPetId(Long id) {
        if (repository.existsById(id)) {
            System.out.println("------found id " + id);
        } else {
            System.out.println("------did not find " + id);
        }
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
