package in.nic.smart_contact_manager.repositories;

import in.nic.smart_contact_manager.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

//@RepositoryRestResource(excerptProjection = UserProjection.class)  // use for fix projection
public interface UserRepository extends JpaRepository<User, String> {

    User findByName(String name);
}
