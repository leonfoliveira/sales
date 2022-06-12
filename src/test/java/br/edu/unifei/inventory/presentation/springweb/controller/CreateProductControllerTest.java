package br.edu.unifei.inventory.presentation.springweb.controller;

import br.edu.unifei.inventory.application.contract.CreateProductUsecase;
import br.edu.unifei.inventory.application.contract.CreateProductUsecaseSpy;
import br.edu.unifei.inventory.application.dto.CreateProductDTO;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.entity.ProductMock;
import br.edu.unifei.inventory.domain.entity.UnitType;
import br.edu.unifei.inventory.presentation.springweb.request.CreateProductRequest;
import br.edu.unifei.inventory.presentation.springweb.request.CreateProductRequestMock;
import br.edu.unifei.inventory.presentation.springweb.response.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateProductControllerTest {
    CreateProductUsecase createProductUsecaseSpy;
    CreateProductController sut;

    @BeforeEach
    void setup() {
        createProductUsecaseSpy = CreateProductUsecaseSpy.get();
        sut = new CreateProductController(createProductUsecaseSpy);
    }

    @Test
    void shouldCallCreateProductWithCorrectParams() {
        ArgumentCaptor<CreateProductDTO> argumentCaptor = ArgumentCaptor.forClass(CreateProductDTO.class);
        CreateProductRequest body = CreateProductRequestMock.get();
        sut.handle(body);
        verify(createProductUsecaseSpy).handle(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().title(), body.getTitle());
        assertEquals(argumentCaptor.getValue().barCode(), body.getBarCode());
        assertEquals(argumentCaptor.getValue().unitPrice(), body.getUnitPrice());
        assertEquals(argumentCaptor.getValue().unitType(), UnitType.valueOf(body.getUnitType()));
    }

    @Test
    void shouldReturnAProductResponseOnSuccess() {
        Product product = ProductMock.get();
        when(createProductUsecaseSpy.handle(any())).thenReturn(product);
        ProductResponse response = sut.handle(CreateProductRequestMock.get());
        assertEquals(response, new ProductResponse(product));
    }
}
