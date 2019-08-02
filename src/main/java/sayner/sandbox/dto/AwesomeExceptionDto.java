package sayner.sandbox.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sayner.sandbox.jsontemplate.jview.AwesomeExceptionView;

/**
 * Вот это штука пойдёт в json
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AwesomeExceptionDto {

    @JsonView(AwesomeExceptionView.Message.class)
    private String message;

    @JsonView(AwesomeExceptionView.FullMessage.class)
    private String smthg;

    @JsonView(AwesomeExceptionView.ShowException.class)
    private RuntimeException runtimeException;
}
