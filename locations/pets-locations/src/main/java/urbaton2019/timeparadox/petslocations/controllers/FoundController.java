package urbaton2019.timeparadox.petslocations.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import urbaton2019.timeparadox.petslocations.controllers.dto.MessageDTO;
import urbaton2019.timeparadox.petslocations.service.SearchLocationService;

import javax.annotation.Resource;


@Controller
public class FoundController {

    @Resource
    private SearchLocationService searchLocationService;

    @DeleteMapping("/found/{petId}")
    public ResponseEntity<MessageDTO> deleteSearchLocation(@PathVariable Long petId) {
        try {
            searchLocationService.delete(petId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageDTO(petId, false, "could not delete from db"));
        }
        return ResponseEntity.ok(new MessageDTO(petId, true, ""));
    }
}
