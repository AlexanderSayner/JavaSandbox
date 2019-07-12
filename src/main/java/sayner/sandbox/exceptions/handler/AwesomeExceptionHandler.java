package sayner.sandbox.exceptions.handler;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sayner.sandbox.exceptions.ThereIsNoSuchArticleException;
import sayner.sandbox.jsontemplate.ResponseHandler;
import sayner.sandbox.jsontemplate.jview.AwesomeExceptionView;

/**
 * Вот эта штука говорит Spring boot выдать такое исключение, кооторое хочу видеть я (клиент данного сервиса)
 */
@ControllerAdvice
public class AwesomeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ThereIsNoSuchArticleException.class)
    @JsonView(AwesomeExceptionView.Message.class)
    protected ResponseEntity<Object> hundleThereIsNoSuchArticleException(){

        //return new ResponseEntity<Object>(new AwesomeException("There is no such article. Can't find it.", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);


        Object responseObj = new AwesomeException("There is no such article. Can't find it.");

        ResponseHandler responseHandler = new ResponseHandler();
        return responseHandler.generateResponse(HttpStatus.NOT_FOUND,false,"Internal exception",responseObj);


    }

}
