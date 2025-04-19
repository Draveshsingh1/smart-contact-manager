package in.nic.smart_contact_manager.services;

import in.nic.smart_contact_manager.entities.User;
import in.nic.smart_contact_manager.exceptions.ResourceNotFoundException;
import in.nic.smart_contact_manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        UserDetails customDetails = new CustomUserDetails(user);
        return customDetails;
    }
}
