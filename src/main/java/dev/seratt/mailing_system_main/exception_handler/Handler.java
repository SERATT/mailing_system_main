package dev.seratt.mailing_system_main.exception_handler;

import dev.seratt.mailing_system_main.exception.InvalidDataInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handler {
    @ExceptionHandler
    public ResponseEntity<InvalidDataInfo> handleException(NumberFormatException exception){
        InvalidDataInfo info = new InvalidDataInfo("Bad request: " +
                exception.getMessage());
        return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
    }
}
