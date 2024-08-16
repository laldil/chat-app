package kz.edu.astanait.dashboard.exceptions.handler;

import kz.edu.astanait.dashboard.controller.api.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse handleInvalidArgument(MethodArgumentNotValidException e) {
        return ApiErrorResponse.create(Objects.requireNonNull(e.getFieldError()).getDefaultMessage());
    }
}
