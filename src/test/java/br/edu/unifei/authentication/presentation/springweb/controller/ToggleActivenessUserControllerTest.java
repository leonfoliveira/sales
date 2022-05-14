package br.edu.unifei.authentication.presentation.springweb.controller;

import br.edu.unifei.authentication.application.contract.ToggleActivenessUserUsecase;
import br.edu.unifei.authentication.application.contract.ToggleActivenessUserUsecaseSpy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.verify;

class ToggleActivenessUserControllerTest {
    ToggleActivenessUserUsecase toggleActivenessUserUsecase;
    ToggleActivenessUserController sut;

    @BeforeEach
    void setup() {
        toggleActivenessUserUsecase = ToggleActivenessUserUsecaseSpy.get();
        sut = new ToggleActivenessUserController(toggleActivenessUserUsecase);
    }

    @Test
    void shouldCallToggleActivenessUserUsecaseWithCorrectParams() {
        String userId = UUID.randomUUID().toString();
        sut.handle(userId);
        verify(toggleActivenessUserUsecase).handle(UUID.fromString(userId));
    }
}