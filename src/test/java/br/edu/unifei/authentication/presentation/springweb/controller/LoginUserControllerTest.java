package br.edu.unifei.authentication.presentation.springweb.controller;

import br.edu.unifei.authentication.application.contract.LoginUserUsecase;
import br.edu.unifei.authentication.application.contract.LoginUserUsecaseSpy;
import br.edu.unifei.authentication.application.model.Authorization;
import br.edu.unifei.authentication.application.model.AuthorizationMock;
import br.edu.unifei.authentication.presentation.springweb.request.LoginUserRequest;
import br.edu.unifei.authentication.presentation.springweb.request.LoginUserRequestMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LoginUserControllerTest {
    LoginUserUsecase loginUserUsecaseSpy = LoginUserUsecaseSpy.get();
    LoginUserController sut;

    @BeforeEach
    void setup() {
        loginUserUsecaseSpy = LoginUserUsecaseSpy.get();
        sut = new LoginUserController(loginUserUsecaseSpy);
    }

    @Test
    void shouldCallLoginUserUsecaseWithCorrectParams() {
        LoginUserRequest body = LoginUserRequestMock.get();
        sut.handle(body);
        verify(loginUserUsecaseSpy).handle(body.getLogin(), body.getPassword());
    }

    @Test
    void shouldReturnAnAuthorizationModelOnSuccess() {
        Authorization authorization = AuthorizationMock.get();
        when(loginUserUsecaseSpy.handle(any(), any()))
                .thenReturn(authorization);
        Authorization result = sut.handle(LoginUserRequestMock.get());
        assertEquals(result, authorization);
    }
}
