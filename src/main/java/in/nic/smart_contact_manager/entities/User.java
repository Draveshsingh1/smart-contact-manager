package in.nic.smart_contact_manager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 to 100 characters")
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be between 6 to 100 characters")
    private String password;

    @Column(length = 5000)
    @Size(max = 5000, message = "About section must be at most 5000 characters")
    private String about;

    @Column(length = 1000)
    @Size(max = 1000, message = "Profile picture URL must be at most 1000 characters")
    private String profilePicture;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;

    private boolean enabled = true;

    private boolean emailVerified = false;

    private boolean phoneNumberVerified = false;

    @Enumerated(EnumType.STRING)
    private Providers providers = Providers.SELF;

    private String providerUserId;

    @Size(max = 255, message = "Email token must be at most 255 characters")
    private String emailToken;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Contact> contacts = new LinkedHashSet<>();
}
