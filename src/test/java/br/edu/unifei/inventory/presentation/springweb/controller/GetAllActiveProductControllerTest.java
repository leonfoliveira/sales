package br.edu.unifei.inventory.presentation.springweb.controller;

import br.edu.unifei.inventory.application.contract.GetAllProductUsecase;
import br.edu.unifei.inventory.application.contract.GetAllProductUsecaseSpy;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.entity.ProductMock;
import br.edu.unifei.inventory.presentation.springweb.response.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAllActiveProductControllerTest {
    GetAllProductUsecase getAllProductUsecaseSpy;
    GetAllActiveProductController sut;

    @BeforeEach
    void setup() {
        getAllProductUsecaseSpy = GetAllProductUsecaseSpy.get();
        sut = new GetAllActiveProductController(getAllProductUsecaseSpy);
    }

    @Test
    void shouldCallGetAllActiveProductUsecase() {
        sut.handle();
        verify(getAllProductUsecaseSpy).handle(false);
    }

    @Test
    void shouldReturnAListOfProductResponseOnSuccess() {
        List<Product> products = List.of(ProductMock.get());
        when(getAllProductUsecaseSpy.handle(false)).thenReturn(products);
        List<ProductResponse> responses = sut.handle();
        assertEquals(responses, products.stream().map(ProductResponse::new).toList());
    }
}
