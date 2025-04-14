package in.nic.smart_contact_manager.entities.event;

import in.nic.smart_contact_manager.entities.Contact;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RepositoryEventHandler(Contact.class)
public class ContactEventHandler {

    @HandleBeforeCreate
    public void handleBeforeCreate(Contact contact){
      contact.setId(UUID.randomUUID().toString());

    }
}
