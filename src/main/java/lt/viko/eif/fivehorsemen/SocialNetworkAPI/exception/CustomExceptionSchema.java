package lt.viko.eif.fivehorsemen.SocialNetworkAPI.exception;

/**
 * The CustomExceptionSchema class is for creating custom
 * expectations for SocialNetworkAPI
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public class CustomExceptionSchema {

    private String message;
    private int errorCode;

    /**
     * default constructor
     */
    protected CustomExceptionSchema() {
    }

    /**
     * CustomExceptionSchema constructor
     * @param message custom message
     * @param errorCode code of error
     */

    public CustomExceptionSchema(
            String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    /**
     * Gets error message
     * @return a String
     */

    public String getMessage() {
        return message;
    }

    /**
     * Gets error code
     * @return a Integer
     */

    public int getErrorCode() {
        return errorCode;
    }
}
