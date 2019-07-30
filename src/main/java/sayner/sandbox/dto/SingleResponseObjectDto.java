package sayner.sandbox.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.annotation.Transient;
import sayner.sandbox.jsontemplate.jview.SingleResponseObjectDtoView;

import java.time.LocalDateTime;
import java.util.EnumMap;

@NoArgsConstructor
@Getter
@Log4j2
public class SingleResponseObjectDto<T> {

    @Transient
//    @Getter(value = AccessLevel.PRIVATE)
    private final EnumMap<StatusEnum, StatusCodeEnum> errorCodeEnum =
            new EnumMap<StatusEnum, StatusCodeEnum>(StatusEnum.class);

    @JsonView(SingleResponseObjectDtoView.StatusCode.class)
    private StatusCodeEnum statusCode;

    @JsonView(SingleResponseObjectDtoView.Message.class)
    private String message;

    @JsonView(SingleResponseObjectDtoView.Success.class)
    private Boolean success;

    @JsonView(SingleResponseObjectDtoView.DataOrException.class)
    private T dataOrException;

    @JsonView(SingleResponseObjectDtoView.OperationDateAndTime.class)
    private LocalDateTime operationDateAndTime;

    public SingleResponseObjectDto(StatusEnum status, String message, Boolean success, T dataOrException) {

        this.statusCode = this.errorCodeEnum.get(status);
        this.message = message;
        this.success = success;
        this.dataOrException = dataOrException;
        this.operationDateAndTime = LocalDateTime.now();
    }
}
