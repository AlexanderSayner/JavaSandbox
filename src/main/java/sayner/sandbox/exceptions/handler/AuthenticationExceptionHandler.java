package sayner.sandbox.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;


@Component
public class AuthenticationExceptionHandler implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        HttpStatus my_status;

        switch (response.getStatus()) {

            case 403:
                my_status = HttpStatus.FORBIDDEN;
                break;

            default:
                my_status = HttpStatus.UNAUTHORIZED;
                break;

        }

        String responseMsg = "{ \"http status\" : \"" + my_status + "\", \"response status\" : \"" + response.getStatus() + "\", \"message\" : \"Костыльный json\", \"error\" : \"" + authException.getMessage() + "\" }";
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(responseMsg);

        /**
         * Далее вот эта штука @Autowired в SecurityConfiguration (WebSecurityConfiguration)
         */
    }
}