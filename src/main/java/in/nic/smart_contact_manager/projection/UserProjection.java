package in.nic.smart_contact_manager.projection;

import in.nic.smart_contact_manager.entities.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "user-projection",types = {User.class})
public interface UserProjection {
    String getName();
    String getEmail();
    String getAbout();

}
