package am.rockstars.security.filter;

import am.rockstars.security.domain.LoginPayload;
import am.rockstars.security.jwt.JWTAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        this.setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp)
            throws AuthenticationException, IOException {

        LoginPayload loginPayload = new ObjectMapper().readValue(req.getInputStream(), LoginPayload.class);

        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(loginPayload.getUsername(),
                loginPayload.getPassword())
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse resp,
                                            FilterChain chain, Authentication auth) {
        String token = JWTAuthenticationService.generateAuthHeader(auth);
        resp.addHeader("Authorization", token);
    }
}
