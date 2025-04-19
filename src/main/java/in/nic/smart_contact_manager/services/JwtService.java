package in.nic.smart_contact_manager.services;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class JwtService {
    private static final String SECRET_KEY = "MySuperSecureSecretKeyThatIsForSecureMyApplication";
    private static final long ACCESS_TOKEN_EXPIRATION = 15 * 60 * 1000;
    private static final long REFRESH_TOKEN_EXPIRATION = 48 * 60 * 60 * 1000;

    public String generateToken( String userName , boolean isAccessToken){
        long expiration = isAccessToken ? ACCESS_TOKEN_EXPIRATION : REFRESH_TOKEN_EXPIRATION ;
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUserFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(token);
            return  true;
        }
        catch (JwtException exception){
            return false;
        }

    }
}
