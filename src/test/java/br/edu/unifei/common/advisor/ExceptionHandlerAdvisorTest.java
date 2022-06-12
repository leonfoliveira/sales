package br.edu.unifei.common.advisor;

import br.edu.unifei.common.exception.*;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExceptionHandlerAdvisorTest {
    ExceptionHandlerAdvisor sut = new ExceptionHandlerAdvisor();
    Faker faker = new Faker();

    @Test
    void shouldHandleConflictExceptionCorrectly() {
        String message = faker.lorem().sentence();
        ConflictException ex = new ConflictException(message);
        ExceptionResponse response = sut.handle(ex);
        assertEquals(response, new ExceptionResponse(ex.getMessage()));
    }

    @Test
    void shouldHandleForbiddenExceptionCorrectly() {
        String message = faker.lorem().sentence();
        ForbiddenException ex = new ForbiddenException(message);
        ExceptionResponse response = sut.handle(ex);
        assertEquals(response, new ExceptionResponse(ex.getMessage()));
    }

    @Test
    void shouldHandleNotFoundExceptionCorrectly() {
        String message = faker.lorem().sentence();
        NotFoundException ex = new NotFoundException(message);
        ExceptionResponse response = sut.handle(ex);
        assertEquals(response, new ExceptionResponse(ex.getMessage()));
    }

    @Test
    void shouldHandleUnauthorizedExceptionCorrectly() {
        String message = faker.lorem().sentence();
        UnauthorizedException ex = new UnauthorizedException(message);
        ExceptionResponse response = sut.handle(ex);
        assertEquals(response, new ExceptionResponse(ex.getMessage()));
    }

    @Test
    void shouldHandleBusinessRuleExceptionCorrectly() {
        String message = faker.lorem().sentence();
        BusinessRuleException ex = new BusinessRuleException(message);
        ExceptionResponse response = sut.handle(ex);
        assertEquals(response, new ExceptionResponse(ex.getMessage()));
    }

    @Test
    void shouldHandleAccessDeniedExceptionCorrectly() {
        AccessDeniedException ex = new AccessDeniedException("");
        ExceptionResponse response = sut.handle(ex);
        assertEquals(response, new ExceptionResponse("Access denied."));
    }

    @Test
    void shouldHandleHttpRequestMethodNotSupportedExceptionCorrectly() {
        HttpRequestMethodNotSupportedException ex = new HttpRequestMethodNotSupportedException("");
        ExceptionResponse response = sut.handle(ex);
        assertEquals(response, new ExceptionResponse("Method not allowed."));
    }

    @Test
    void shouldHandleAnyOtherExceptionCorrectly() {
        Exception ex = new Exception();
        ExceptionResponse response = sut.handle(ex);
        assertEquals(response, new ExceptionResponse("Internal server error."));
    }
}
