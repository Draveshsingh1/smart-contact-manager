package in.nic.smart_contact_manager.entities.event;

import in.nic.smart_contact_manager.entities.Role;
import in.nic.smart_contact_manager.entities.User;
import in.nic.smart_contact_manager.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RepositoryEventHandler(User.class)
public class UserEventHandler {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @HandleBeforeCreate
    public void handleBeforeCreate(User user){
        user.setId(UUID.randomUUID().toString());
        Role role = roleRepository.findById(3L)
                .orElseThrow(() -> new RuntimeException("Role Not Found"));
        user.getRoles().add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @HandleBeforeSave
    public void handleBeforeSave(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

}
