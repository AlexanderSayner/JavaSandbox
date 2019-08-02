package sayner.sandbox.controllers.controllerAdvice;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sayner.sandbox.dto.AwesomeExceptionDto;
import sayner.sandbox.dto.SingleResponseObjectDto;
import sayner.sandbox.dto.StatusEnum;
import sayner.sandbox.dto.extd.SingleResponseObjectDtpExt;
import sayner.sandbox.exceptions.NotFoundByIdException;
import sayner.sandbox.exceptions.ThereIsNoSuchArticleException;
import sayner.sandbox.jsontemplate.jview.SingleResponseObjectDtoView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler implements AccessDeniedHandler {

    @ExceptionHandler({ThereIsNoSuchArticleException.class})
    @JsonView(SingleResponseObjectDtoView.StatusCodeMessageSuccessDataOrExceptionOperationDateAndTimeWithAwesomeException.class)
    public SingleResponseObjectDto handleThereIsNoSuchArticleException(ThereIsNoSuchArticleException tinsae) throws IOException {

        SingleResponseObjectDto stringSingleResponseObjectDto = new SingleResponseObjectDtpExt<>(
                StatusEnum.ArticleEntityIsNull,
                "Article не найден",
                false,
                new AwesomeExceptionDto(ThereIsNoSuchArticleException.class.toString(), tinsae.getMessage(), tinsae)
        );

        return stringSingleResponseObjectDto;
    }

    @ExceptionHandler({NotFoundByIdException.class})
    @JsonView(SingleResponseObjectDtoView.StatusCodeMessageSuccessDataOrExceptionOperationDateAndTimeWithAwesomeException.class)
    public SingleResponseObjectDto handleNotFoundById(NotFoundByIdException nfbie) throws IOException {

        SingleResponseObjectDto stringSingleResponseObjectDto = new SingleResponseObjectDtpExt<>(
                StatusEnum.ArticleIdDoesNotExist,
                "Такого Id не существует",
                false,
                new AwesomeExceptionDto(NotFoundByIdException.class.toString(), nfbie.getMessage(), nfbie)
        );

        return stringSingleResponseObjectDto;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

    }

}
