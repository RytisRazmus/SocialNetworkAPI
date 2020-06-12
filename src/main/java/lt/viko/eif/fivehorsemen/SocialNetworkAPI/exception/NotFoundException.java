package lt.viko.eif.fivehorsemen.SocialNetworkAPI.exception;

/**
 * The NotFoundException class is for exception handling
 * extends RuntimeException class
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public class NotFoundException extends RuntimeException {

    private int errorCode;
    private String errorMessage;

    /**
     * NotFoundException constructor
     * @param message error message
     * @param errorCode error code
     */
    public NotFoundException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.errorMessage = message;
    }

    /**
     * Sets error code
     * @param errorCode a int
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets error code
     * @return a int
     */

    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Sets error message
     * @param errorMessage a String
     */

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets error message
     * @return a String
     */

    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Override method of toString
     * @return error code and error message
     */

    @Override
    public String toString() {
        return this.errorCode + " : " + this.getErrorMessage();
    }
}
