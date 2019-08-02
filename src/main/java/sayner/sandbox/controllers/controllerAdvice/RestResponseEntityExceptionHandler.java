package sayner.sandbox.controllers.controllerAdvice;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sayner.sandbox.dto.SingleResponseObjectDto;
import sayner.sandbox.dto.StatusEnum;
import sayner.sandbox.dto.extd.SingleResponseObjectDtoExtd;
import sayner.sandbox.exceptions.NotFoundByIdException;
import sayner.sandbox.exceptions.ThereIsNoSuchArticleException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler implements AccessDeniedHandler {

    @ExceptionHandler({ThereIsNoSuchArticleException.class})
    public SingleResponseObjectDto handleThereIsNoSuchArticleException(ThereIsNoSuchArticleException tinsae) throws IOException {

        SingleResponseObjectDto stringSingleResponseObjectDto = new SingleResponseObjectDtoExtd<>(
                StatusEnum.ArticleEntityIsNull,
                "Article не найден",
                false,
                ThereIsNoSuchArticleException.class
        );

        return stringSingleResponseObjectDto;
    }

    @ExceptionHandler({NotFoundByIdException.class})
    public SingleResponseObjectDto handleNotFoundById(NotFoundByIdException nfbie) throws IOException {

        SingleResponseObjectDto stringSingleResponseObjectDto = new SingleResponseObjectDtoExtd<>(
                StatusEnum.ArticleIdDoesNotExist,
                "Такого Id не существует",
                false,
                NotFoundByIdException.class
        );

        return stringSingleResponseObjectDto;
    }



    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {


    }

}
