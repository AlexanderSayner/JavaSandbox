package sayner.sandbox.jsontemplate.jview;

public class SingleResponseObjectDtoView {

    public interface StatusCode {
    }

    public interface Message {
    }

    public interface Success {
    }

    public interface DataOrException {
    }

    public interface OperationDateAndTime {
    }

    public interface StatusCodeMessage extends StatusCode, Message {
    }

    public interface StatusCodeMessageSuccess extends StatusCode, Message, Success {
    }

    public interface StatusCodeMessageSuccessDataOrException extends StatusCodeMessageSuccess {
    }

    public interface StatusCodeMessageSuccessDataOrExceptionOperationDateAndTimeWithArticle extends StatusCodeMessageSuccessDataOrException, ArticleViewDto.FullArticle {
    }

    public interface StatusCodeMessageSuccessDataOrExceptionOperationDateAndTimeWithAwesomeException extends StatusCodeMessageSuccessDataOrException, AwesomeExceptionView.FullAwesomeException {
    }

    public interface StatusCodeMessageDataOrException extends StatusCodeMessage {
    }

    public interface StatusCodeMessageDataOrExceptionOperationDateAndTime extends
            StatusCodeMessageDataOrException,
            OperationDateAndTime,
            ArticleViewDto.IdStateTitleManufacturerNameCreationDate {
    }
}
