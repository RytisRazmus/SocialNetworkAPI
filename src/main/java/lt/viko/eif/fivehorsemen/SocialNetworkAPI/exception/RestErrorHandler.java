package lt.viko.eif.fivehorsemen.SocialNetworkAPI.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The RestErrorHandler class is for Rest error handling
 * extends ResponseEntityExceptionHandler class
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles NotFoundException errors
     * @param ex NotFoundException object
     * @return response of exception
     */

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleAllExceptions(NotFoundException ex) {
        CustomExceptionSchema exceptionResponse =
                new CustomExceptionSchema(
                        ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity(exceptionResponse, HttpStatus.valueOf(ex.getErrorCode()));
    }
}
