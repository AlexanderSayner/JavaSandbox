package sayner.sandbox.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import sayner.sandbox.authorization.UserData;
import sayner.sandbox.jsontemplate.ModelResponse;

import java.io.IOException;

/**
 * This class describes the result of user authorization.
 */
@Component
public class AuthenticationEventListener implements ApplicationListener<AbstractAuthenticationEvent> {

    @Autowired
    private ModelResponse modelResponse;


    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent authenticationEvent) {
        if (authenticationEvent instanceof InteractiveAuthenticationSuccessEvent) {
            return;
        }

        /**
         * Formation of server response (via ModelResponse) with successful or unsuccessful authorization
         */
        Authentication authentication = authenticationEvent.getAuthentication();
        String authMessage;
        UserData userData;

        if(authentication.isAuthenticated()) {
            authMessage = "Success login";
            userData = new UserData(authentication.getName(), authentication.getAuthorities(), authentication.getCredentials(), authentication.getPrincipal());
            try {
                modelResponse.responseEntity(HttpStatus.ACCEPTED, authMessage, userData, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            authMessage = "Not success login";
            try {
                modelResponse.responseEntity(HttpStatus.UNAUTHORIZED, authMessage, null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}