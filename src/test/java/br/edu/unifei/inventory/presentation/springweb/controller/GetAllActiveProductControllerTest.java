package br.edu.unifei.inventory.presentation.springweb.controller;

import br.edu.unifei.inventory.application.contract.GetAllProductUsecase;
import br.edu.unifei.inventory.application.contract.GetAllProductUsecaseSpy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;

public class GetAllProductControllerTest {
    GetAllProductUsecase getAllProductUsecaseSpy;
    GetAllProductController sut;

    @BeforeEach
    void setup() {
        getAllProductUsecaseSpy = GetAllProductUsecaseSpy.get();
        sut = new GetAllProductController(getAllProductUsecaseSpy);
    }

    @Test
    void shouldCallGetAllProductUsecase() {
        sut.handle();
        verify(getAllProductUsecaseSpy).handle();
    }
}
