package br.edu.unifei.authentication.presentation.springweb.controller;

import br.edu.unifei.authentication.application.contract.SetPasswordUserUsecase;
import br.edu.unifei.authentication.application.contract.SetPasswordUserUsecaseSpy;
import br.edu.unifei.authentication.presentation.springweb.request.SetPasswordUserRequest;
import br.edu.unifei.authentication.presentation.springweb.request.SetPasswordUserRequestMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.verify;

class SetPasswordUserControllerTest {
    SetPasswordUserUsecase setPasswordUserUsecaseSpy;
    SetPasswordUserController sut;

    @BeforeEach
    void setup() {
        setPasswordUserUsecaseSpy = SetPasswordUserUsecaseSpy.get();
        sut = new SetPasswordUserController(setPasswordUserUsecaseSpy);
    }

    @Test
    void shouldCallSetPasswordUserUsecaseWithCorrectParams() {
        String userId = UUID.randomUUID().toString();
        SetPasswordUserRequest body = SetPasswordUserRequestMock.get();
        sut.handle(userId, body);
        verify(setPasswordUserUsecaseSpy).handle(UUID.fromString(userId), body.getPassword());
    }
}
