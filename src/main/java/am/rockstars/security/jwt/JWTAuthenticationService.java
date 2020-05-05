package am.rockstars.security.jwt;

import am.rockstars.security.domain.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JWTAuthenticationService {

    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;

    private static final String SECRET = "ZQgqEFsl5OxjgcTxfSlZv3jPMb1aWOAT";

    private static final String AUTHORITIES = "Authorities";

    private static final String ID = "id";

    private static final String SUBJECT = "sub";

    private static final String TOKEN_PREFIX = "Bearer";

    public static String generateAuthHeader(Authentication auth) {

        final List<String> authorities = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        final Map<String, Object> claims = new HashMap<>();

        claims.put(ID, ((UserPrincipal) auth.getPrincipal()).getId());
        claims.put(SUBJECT, ((UserPrincipal) auth.getPrincipal()).getUsername());
        claims.put(AUTHORITIES, authorities);

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        return TOKEN_PREFIX + " " + jwt;
    }

    public static Authentication parseAuthHeader(String authHeader) {

        String authToken = authHeader != null ? authHeader.replace(TOKEN_PREFIX, "") : null;

        if (authToken != null) {

            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(authToken)
                    .getBody();

            String username = claims.getSubject();

            @SuppressWarnings("unchecked") final List<String> authoritiesClaim = (List<String>) claims.get(AUTHORITIES);

            final List<SimpleGrantedAuthority> authorities = authoritiesClaim
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            return username != null ?
                    new UsernamePasswordAuthenticationToken(username, null, authorities) :
                    null;
        }
        return null;
    }

}
