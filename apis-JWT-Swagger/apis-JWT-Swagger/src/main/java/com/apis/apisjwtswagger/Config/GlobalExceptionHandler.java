package com.apis.apisjwtswagger.Config;

import com.apis.apisjwtswagger.DTO.ErrorManagerClass;
import com.apis.apisjwtswagger.Exceptions.NoCommentFoundException;
import com.apis.apisjwtswagger.Exceptions.NoPostsFoundException;
import com.apis.apisjwtswagger.Exceptions.UserNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorManagerClass> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorManagerClass errorManagerClass = new ErrorManagerClass(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.getMessage(),
            LocalDateTime.now()
                );

        return new ResponseEntity<>(errorManagerClass, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(NoPostsFoundException.class)
    public ResponseEntity<ErrorManagerClass> handleNoPostsFoundException(NoPostsFoundException ex) {
        ErrorManagerClass errorManagerClass = new ErrorManagerClass(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorManagerClass, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoCommentFoundException.class)
    public ResponseEntity<ErrorManagerClass> handleNoCommentFoundException(NoCommentFoundException ex) {
        ErrorManagerClass errorManagerClass = new ErrorManagerClass(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorManagerClass, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorManagerClass> exceptionController
            (MethodArgumentNotValidException exception) {

        List<String> errores = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        ErrorManagerClass error = new ErrorManagerClass(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Arguments not valid",
                LocalDateTime.now(),
                errores
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }




}
