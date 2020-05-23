package lt.viko.eif.fivehorsemen.SocialNetworkAPI.exception;

public class CustomExceptionSchema {

    private String message;
    private int errorCode;

    protected CustomExceptionSchema() {
    }

    public CustomExceptionSchema(
            String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
