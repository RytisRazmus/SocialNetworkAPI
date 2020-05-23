package lt.viko.eif.fivehorsemen.SocialNetworkAPI.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleAllExceptions(NotFoundException ex) {
        CustomExceptionSchema exceptionResponse =
                new CustomExceptionSchema(
                        ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity(exceptionResponse, HttpStatus.valueOf(ex.getErrorCode()));
    }
}
