package in.anandm.pa.web.security.jwt;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class JWTAuthenticationToken implements Authentication {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String name;

    private Collection<? extends GrantedAuthority> authorities;

    private String principal;

    private boolean authenticated;

    @Override
    public String getName() {

        return name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public Object getCredentials() {

        return null;
    }

    @Override
    public Object getDetails() {

        return null;
    }

    @Override
    public Object getPrincipal() {

        return principal;
    }

    @Override
    public boolean isAuthenticated() {

        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated)
            throws IllegalArgumentException {
        if (isAuthenticated == true) {
            throw new IllegalArgumentException("potential security risk");
        }
        else {
            this.authenticated = isAuthenticated;
        }
    }

    public static JWTAuthenticationToken fromToken(String jwtToken) {

    }
}
