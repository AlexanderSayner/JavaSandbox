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

    public interface StatusCodeMessage extends StatusCode {
    }

    public interface StatusCodeMessageSuccess extends StatusCodeMessage {
    }

    public interface StatusCodeMessageSuccessDataOrException extends StatusCodeMessageSuccess {
    }

    public interface StatusCodeMessageSuccessDataOrExceptionOperationDateAndTime extends StatusCodeMessageSuccessDataOrException {
    }

    public interface StatusCodeMessageDataOrException extends StatusCodeMessage {
    }
}
