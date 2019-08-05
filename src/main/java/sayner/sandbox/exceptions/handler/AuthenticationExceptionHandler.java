package sayner.sandbox.exceptions.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import sayner.sandbox.dto.StatusEnum;
import sayner.sandbox.dto.extd.SingleResponseObjectDtpExt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

@NoArgsConstructor
@Component
public class AuthenticationExceptionHandler implements AuthenticationEntryPoint, Serializable {

    private Boolean isForbidden = false;

    public AuthenticationExceptionHandler(Boolean isForbidden) {
        this.isForbidden = isForbidden;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        StatusEnum my_status = StatusEnum.Unauthorized;

        if (this.isForbidden) {
            my_status = StatusEnum.NoAccess;
        }

        SingleResponseObjectDtpExt<Object> singleResponseObjectDtpExt = new SingleResponseObjectDtpExt<>(
                my_status, "Нет доступа или указаны неверные данные пользователя, response status: " + response.getStatus(), false, authException.getMessage());

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
