package in.nic.smart_contact_manager.controllers;

import in.nic.smart_contact_manager.entities.User;
import in.nic.smart_contact_manager.payload.AuthRequest;
import in.nic.smart_contact_manager.payload.RefreshTokenRequest;
import in.nic.smart_contact_manager.repositories.UserRepository;
import in.nic.smart_contact_manager.response.JwtResponse;
import in.nic.smart_contact_manager.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scm/auth")
public class AuthController {
    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private  UserDetailsService userDetailsService;

    @Autowired
    private  JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
         authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

        String accessToken = jwtService.generateToken(request.getEmail(), true);
        String refreshToken = jwtService.generateToken(request.getEmail(), false);

        User user = userRepository.findByEmail(request.getEmail()).get();
        JwtResponse jwtResponse = new JwtResponse(accessToken, refreshToken, user);
        return ResponseEntity.ok(jwtResponse);

    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request){
        if(jwtService.validateToken(request.getRefreshToken())){
            String userNameFromToken = jwtService.getUserFromToken(request.getRefreshToken());
            String accessToken = jwtService.generateToken(userNameFromToken, true);
            String refreshToken = jwtService.generateToken(userNameFromToken, false);

            User user = userRepository.findByEmail(userNameFromToken).get();
            JwtResponse jwtResponse = new JwtResponse(accessToken, refreshToken, user);
            return ResponseEntity.ok(jwtResponse);
        }

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Refresh Token");

    }

}
