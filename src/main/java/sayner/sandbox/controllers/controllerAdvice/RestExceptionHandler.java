package sayner.sandbox.controllers.controllerAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sayner.sandbox.ausgenommen.ModelException;
import sayner.sandbox.ausgenommen.ThereIsNoSuchArticleException;
import sayner.sandbox.jsonmuster.ModelResponse;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import java.io.File;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler implements AccessDeniedHandler {

    @ExceptionHandler(ThereIsNoSuchArticleException.class)
    public ResponseEntity<Object> handlerEntityNotFound(HttpServletResponse statusCode) throws IOException {

        /**
         * Add new object ModelException with parameters
         */
        ModelException me = new ModelException("Shops not found", EntityNotFoundException.class);


        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.writeValue(new File("/home/uarchon/Development/response.json"), me);


        ModelResponse modelResponse = new ModelResponse();

        /**
         * Return json response in page
         */
        return modelResponse.responseEntity(HttpStatus.OK, "Not success",null, me);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

    }

}