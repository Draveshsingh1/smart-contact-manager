package in.nic.smart_contact_manager.entities.event;

import in.nic.smart_contact_manager.entities.User;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RepositoryEventHandler(User.class)
public class UserEventHandler {

    @HandleBeforeCreate
    public void handleBeforeCreate(User user){
        user.setId(UUID.randomUUID().toString());
    }

}
