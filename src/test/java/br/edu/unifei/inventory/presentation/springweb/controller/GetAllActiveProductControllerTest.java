package br.edu.unifei.inventory.presentation.springweb.controller;

import br.edu.unifei.inventory.application.contract.GetAllActiveProductUsecaseSpy;
import br.edu.unifei.inventory.application.contract.GetAllProductUsecase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;

public class GetAllActiveProductControllerTest {
    GetAllProductUsecase getAllActiveProductUsecaseSpy;
    GetAllActiveProductController sut;

    @BeforeEach
    void setup() {
        getAllActiveProductUsecaseSpy = GetAllActiveProductUsecaseSpy.get();
        sut = new GetAllActiveProductController(getAllActiveProductUsecaseSpy);
    }

    @Test
    void shouldCallGetAllActiveProductUsecase() {
        sut.handle();
        verify(getAllActiveProductUsecaseSpy).handle(false);
    }
}
