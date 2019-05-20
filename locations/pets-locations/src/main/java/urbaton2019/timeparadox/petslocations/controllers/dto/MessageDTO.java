package urbaton2019.timeparadox.petslocations.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDTO {

    private long petId;
    private boolean wasRequestSuccessful;
    private String message;
}
