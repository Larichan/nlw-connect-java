package net.larichan.nlw_connect.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import net.larichan.nlw_connect.dto.ErrorMessage;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEventNotFoundException(EventNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(SubscriptionConflictException.class)
    public ResponseEntity<ErrorMessage> handleSubscriptionConflictException(SubscriptionConflictException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(UserIndicationNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleUserIndicationNotFoundException(
            UserIndicationNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(UserConflictException.class)
    public ResponseEntity<ErrorMessage> handleUserConflictException(UserConflictException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(exception.getAllErrors().stream()
                .map(error -> error.getDefaultMessage()).collect(Collectors.joining(", "))));
    }
}
