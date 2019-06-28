package sayner.sandbox.ausgenommen;

public class ModelException {

    /**
     * Error message
     */
    private String exceptionMessage;

    /**
     * Info about exception class
     */
    private Class exceptionName;

    /**
     * Default constructor
     */
    public ModelException() {

    }

    public ModelException(String exceptionMessage, Class exceptionName) {
        this.exceptionMessage = exceptionMessage;
        this.exceptionName = exceptionName;
    }

    /* Getters / Setters */

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public Class getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(Class exceptionName) {
        this.exceptionName = exceptionName;
    }

    @Override
    public String toString() {
        return "ModelException{" +
                "exceptionMessage='" + exceptionMessage + '\'' +
                ", exceptionName=" + exceptionName +
                '}';
    }
}
