package br.edu.unifei.inventory.presentation.springweb.controller;

import br.edu.unifei.inventory.application.contract.ToggleActivenessProductUsecase;
import br.edu.unifei.inventory.application.contract.ToggleActivenessProductUsecaseSpy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.verify;

public class ToggleActivenessProductControllerTest {
    ToggleActivenessProductUsecase toggleActivenessProductUsecaseSpy;
    ToggleActivenessProductController sut;

    @BeforeEach
    void setup() {
        toggleActivenessProductUsecaseSpy = ToggleActivenessProductUsecaseSpy.get();
        sut = new ToggleActivenessProductController(toggleActivenessProductUsecaseSpy);
    }

    @Test
    void shouldCallToggleActivenessProductUsecaseWithCorrectParams() {
        UUID productId = UUID.randomUUID();
        sut.handle(productId.toString());
        verify(toggleActivenessProductUsecaseSpy).handle(productId);
    }

}
