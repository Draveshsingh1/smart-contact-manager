package in.nic.smart_contact_manager.response;

import in.nic.smart_contact_manager.entities.User;

public record JwtResponse(
        String accessToken,
        String refreshToken,
        User user

) {


}
