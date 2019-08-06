package sayner.sandbox.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * This class describe results of authorization
 */
public class UserData {

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Authorities")
    private Collection<? extends GrantedAuthority> authorities;

    @JsonProperty(value = "Credentials")
    private Object credentials;

    @JsonProperty(value = "Principal")
    private Object principal;

    public UserData(String name, Collection<? extends GrantedAuthority> authorities, Object credentials, Object principal) {
        this.name = name;
        this.authorities = authorities;
        this.credentials = credentials;
        this.principal = principal;
    }
}
