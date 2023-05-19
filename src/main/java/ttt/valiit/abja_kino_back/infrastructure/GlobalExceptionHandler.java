package ttt.valiit.abja_kino_back.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ttt.valiit.abja_kino_back.infrastructure.exception.InvalidCredentialsException;
import ttt.valiit.abja_kino_back.infrastructure.exception.UsernameExistsException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    protected ResponseEntity<Object> handleInvalidCredentials(
            InvalidCredentialsException ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
    @ExceptionHandler(UsernameExistsException.class)
    protected ResponseEntity<Object> handleUsernameExists(
            UsernameExistsException ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(), ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
