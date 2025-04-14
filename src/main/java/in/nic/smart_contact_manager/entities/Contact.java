package in.nic.smart_contact_manager.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_contact")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    @Id
    private String id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 to 100 characters")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

    @Size(max = 1000, message = "Picture URL must not exceed 1000 characters")
    private String picture;

    @Column(length = 5000)
    @Size(max = 5000, message = "Description must not exceed 5000 characters")
    private String description;

    private boolean favourite = false;

    @Size(max = 1000, message = "Website link must not exceed 1000 characters")
    @Pattern(regexp = "^(http(s)?://)?(www\\.)?([\\w-]+\\.)+\\w{2,}(/[\\w-./?%&=]*)?$", message = "Invalid website link")
    private String websiteLink;

    @Size(max = 1000, message = "LinkedIn link must not exceed 1000 characters")
    @Pattern(regexp = "^(http(s)?://)?(www\\.)?linkedin\\.com/.*$", message = "Invalid LinkedIn link")
    private String linkedInLink;

    @Size(max = 1000, message = "Instagram link must not exceed 1000 characters")
    @Pattern(regexp = "^(http(s)?://)?(www\\.)?instagram\\.com/.*$", message = "Invalid Instagram link")
    private String instagramLink;

    @Size(max = 255, message = "Cloudinary image public ID must not exceed 255 characters")
    private String cloudinaryImagePublicId;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}
