package sayner.sandbox.dto.extd;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import sayner.sandbox.dto.SingleResponseObjectDto;
import sayner.sandbox.dto.StatusCodeEnum;
import sayner.sandbox.dto.StatusEnum;
import sayner.sandbox.jsontemplate.jview.ArticleViewDto;
import sayner.sandbox.jsontemplate.jview.SingleResponseObjectDtoView;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Log4j2
public final class SingleResponseObjectDtpExt<T> extends SingleResponseObjectDto {

    @JsonView(SingleResponseObjectDtoView.StatusCode.class)
    private StatusCodeEnum statusCode;

    @JsonView({
            SingleResponseObjectDtoView.Message.class,
            SingleResponseObjectDtoView.StatusCodeMessage.class
    })
    private String message;

    @JsonView({
            SingleResponseObjectDtoView.Success.class,
            SingleResponseObjectDtoView.StatusCodeMessageSuccess.class
    })
    private Boolean success;

    @JsonView({
            SingleResponseObjectDtoView.DataOrException.class,
            SingleResponseObjectDtoView.StatusCodeMessageDataOrException.class,
            SingleResponseObjectDtoView.StatusCodeMessageSuccessDataOrException.class,
            ArticleViewDto.FullArticle.class
    })
    private T dataOrException;

    @JsonView({
            SingleResponseObjectDtoView.OperationDateAndTime.class,
            SingleResponseObjectDtoView.StatusCodeMessageSuccessDataOrExceptionOperationDateAndTime.class
    })

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime operationDateAndTime;

    public SingleResponseObjectDtpExt(StatusEnum status, String message, Boolean success, T dataOrException) {

        this.statusCode = super.getErrorCodeEnum(status);
        this.message = message;
        this.success = success;
        this.dataOrException = dataOrException;
        this.operationDateAndTime = LocalDateTime.now();
    }
}
