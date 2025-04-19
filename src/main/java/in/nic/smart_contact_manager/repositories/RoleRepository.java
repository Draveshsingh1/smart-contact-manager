package in.nic.smart_contact_manager.repositories;

import in.nic.smart_contact_manager.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = true,path = "roles",collectionResourceRel = "roles")
public interface RoleRepository extends JpaRepository<Role,Long> {
}
