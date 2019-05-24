package sayner.sandbox.exceptions.handler;

import com.fasterxml.jackson.annotation.JsonView;
import sayner.sandbox.jsonpattern.jviews.AwesomeExceptionView;

/**
 * Вот это штука пойдёт в json
 */
public class AwesomeException {

    @JsonView(AwesomeExceptionView.Message.class)
    private String message;

    @JsonView(AwesomeExceptionView.FullMessage.class)
    private String smthg;

    /**
     *
     */
    public AwesomeException() {
    }

    public AwesomeException(String message) {
        this.message = message;
        this.smthg = "Ещё что-то";
    }


    /**
     * getter'ы & setter'ы
     */

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSmthg() {
        return smthg;
    }

    public void setSmthg(String smthg) {
        this.smthg = smthg;
    }

}