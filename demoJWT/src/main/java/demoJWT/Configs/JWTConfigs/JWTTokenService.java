package demoJWT.Configs.JWTConfigs;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.Date;

public class JWTTokenService {
    private static final long EXPIRATION_TIME = 86400000;
    private static final String SECRET = new SecureRandom().toString();
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String AUTHORIZATION = "Authorization";

    public static String addJWTTokenToHeaders(HttpServletResponse response, String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static Authentication parseTokenToUserInfomation(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION);
        if (token == null) {
            return null;
        }
        String username = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        return username != null ? new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList()) : null;
    }
}