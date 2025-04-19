package in.nic.smart_contact_manager.repositories;

import in.nic.smart_contact_manager.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@RepositoryRestResource(excerptProjection = UserProjection.class)  // use for fix projection
public interface UserRepository extends JpaRepository<User, String> {

    User findByName(String name);

    Optional<User> findByEmail(String email);
}
