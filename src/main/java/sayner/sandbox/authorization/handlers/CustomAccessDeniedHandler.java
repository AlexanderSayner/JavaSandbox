package sayner.sandbox.authorization.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import sayner.sandbox.dto.StatusEnum;
import sayner.sandbox.dto.extd.SingleResponseObjectDtpExt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Log4j2
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String informString="User: " + auth.getName()
                + " attempted to access the protected URL: "
                + request.getRequestURI();
        if (auth != null) {
            log.warn(informString);
        }

        SingleResponseObjectDtpExt<Object> singleResponseObjectDtpExt = new SingleResponseObjectDtpExt<>(StatusEnum.NoAccess, "Нет доступа", false, informString);

        ObjectMapper mapper = new ObjectMapper();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        mapper.writeValue(out, singleResponseObjectDtpExt
        );
    }
}