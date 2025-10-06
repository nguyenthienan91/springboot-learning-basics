package org.example.studentapi.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class APIExceptionHandler {

    // chạy mỗi khi mà dính lỗi
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleBadRequest(MethodArgumentNotValidException exception) {
        String mess = "";
        for(FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            mess += fieldError.getField() + ": " + fieldError.getDefaultMessage() + "\n";
        }

        return ResponseEntity.badRequest().body(mess);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handleCredentialsException(BadCredentialsException exception) {
        return ResponseEntity.status(401).body("Invalid username or password");
    }

}
