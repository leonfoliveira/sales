package br.edu.unifei.authentication.presentation.springweb.controller;

import br.edu.unifei.authentication.application.contract.ResetPasswordUserUsecase;
import br.edu.unifei.authentication.application.contract.ResetPasswordUserUsecaseSpy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.verify;

class ResetPasswordUserControllerTest {
    ResetPasswordUserUsecase resetPasswordUserUsecaseSpy;
    ResetPasswordUserController sut;

    @BeforeEach
    void setup() {
        resetPasswordUserUsecaseSpy = ResetPasswordUserUsecaseSpy.get();
        sut = new ResetPasswordUserController(resetPasswordUserUsecaseSpy);
    }

    @Test
    void shouldCallResetPasswordUserUsecaseWithCorrectParams() {
        String userId = UUID.randomUUID().toString();
        sut.handle(userId);
        verify(resetPasswordUserUsecaseSpy).handle(UUID.fromString(userId));
    }
}
