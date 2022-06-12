package br.edu.unifei.common.advisor;

import br.edu.unifei.common.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@ControllerAdvice
@RestController
public class ExceptionHandlerAdvisor {
    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handle(ConflictException ex) {
        return new ExceptionResponse(ex.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse handle(ForbiddenException ex) {
        return new ExceptionResponse(ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handle(NotFoundException ex) {
        return new ExceptionResponse(ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse handle(UnauthorizedException ex) {
        return new ExceptionResponse(ex.getMessage());
    }

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handle(BusinessRuleException ex) {
        return new ExceptionResponse(ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse handle(AccessDeniedException ex) {
        return new ExceptionResponse("Access denied.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handle(MethodArgumentNotValidException ex) {
        List<String> errors = Stream.concat(ex.getBindingResult()
                        .getFieldErrors()
                        .stream().map(err -> err.getField() + ": " + err.getDefaultMessage() + "."),
                ex.getBindingResult()
                        .getGlobalErrors()
                        .stream().map(err -> err.getObjectName() + ": " + err.getDefaultMessage() + ".")).toList();
        return new ExceptionResponse(String.join("\n", errors));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ExceptionResponse handle(HttpRequestMethodNotSupportedException ex) {
        return new ExceptionResponse("Method not allowed.");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handle(Exception ex) {
        ex.printStackTrace();
        return new ExceptionResponse("Internal server error.");
    }
}
