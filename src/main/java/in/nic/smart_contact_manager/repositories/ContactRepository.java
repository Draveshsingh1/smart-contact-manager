package in.nic.smart_contact_manager.repositories;

import in.nic.smart_contact_manager.entities.Contact;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = true,path = "contacts",collectionResourceRel = "contacts")
public interface ContactRepository extends JpaRepository<Contact, String> {


    @RestResource(exported = true,path = "by-email",rel = "by-email")
    List<Contact> findByEmailContainingIgnoreCase(@Param("email") String email, Pageable pageable);

    @RestResource(path = "by-phone")
    List<Contact> findByPhoneNumberContainingIgnoreCase(@Param("phone") String phoneNumber);

    @RestResource(path = "by-name")
    List<Contact> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);
}

