package br.edu.unifei.inventory.presentation.springweb.controller;

import br.edu.unifei.inventory.application.contract.GetAllProductUsecase;
import br.edu.unifei.inventory.application.contract.GetAllProductUsecaseSpy;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.entity.ProductMock;
import br.edu.unifei.inventory.presentation.springweb.response.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    void shouldReturnAListOfProductResponseOnSuccess() {
        List<Product> products = List.of(ProductMock.get());
        when(getAllProductUsecaseSpy.handle()).thenReturn(products);
        List<ProductResponse> responses = sut.handle();
        assertEquals(responses, products.stream().map(ProductResponse::new).toList());
    }
}
