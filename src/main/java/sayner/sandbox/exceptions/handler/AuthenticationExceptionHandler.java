package sayner.sandbox.exceptions.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import sayner.sandbox.dto.StatusEnum;
import sayner.sandbox.dto.extd.SingleResponseObjectDtpExt;
import sayner.sandbox.jsontemplate.jview.SingleResponseObjectDtoView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;


@Component
public class AuthenticationExceptionHandler implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        StatusEnum my_status;

        switch (response.getStatus()) {

            case 403:
                my_status = StatusEnum.NoAccess;
                break;

            default:
                my_status = StatusEnum.Unauthorized;
                break;

        }

        SingleResponseObjectDtpExt<Object> singleResponseObjectDtpExt = new SingleResponseObjectDtpExt<>(my_status, "Нет доступа", false, authException.getMessage());

        ObjectMapper mapper = new ObjectMapper();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        mapper.writeValue(out, singleResponseObjectDtpExt
        );

        /**
         * Далее вот эта штука @Autowired в SecurityConfiguration (WebSecurityConfiguration)
         */
    }
}
