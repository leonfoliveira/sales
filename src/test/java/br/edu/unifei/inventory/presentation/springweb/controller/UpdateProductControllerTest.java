package br.edu.unifei.inventory.presentation.springweb.controller;

import br.edu.unifei.inventory.application.contract.UpdateProductUsecase;
import br.edu.unifei.inventory.application.contract.UpdateProductUsecaseSpy;
import br.edu.unifei.inventory.application.dto.UpdateProductDTO;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.entity.ProductMock;
import br.edu.unifei.inventory.presentation.springweb.request.UpdateProductRequest;
import br.edu.unifei.inventory.presentation.springweb.request.UpdateProductRequestMock;
import br.edu.unifei.inventory.presentation.springweb.response.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdateProductControllerTest {
    UpdateProductUsecase updateProductUsecaseSpy;
    UpdateProductController sut;

    @BeforeEach
    void setup(){
        updateProductUsecaseSpy = UpdateProductUsecaseSpy.get();
        sut = new UpdateProductController(updateProductUsecaseSpy);
    }

    @Test
    void shouldCallUpdateProductUsecaseWithCorrectParams() {
        ArgumentCaptor<UpdateProductDTO> argumentCaptor = ArgumentCaptor.forClass(UpdateProductDTO.class);
        UUID productId = UUID.randomUUID();
        UpdateProductRequest body = UpdateProductRequestMock.get();
        sut.handle(productId.toString(), body);
        verify(updateProductUsecaseSpy).handle(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().id(), productId);
        assertEquals(argumentCaptor.getValue().title(), body.getTitle());
        assertEquals(argumentCaptor.getValue().barCode(), body.getBarCode());
        assertEquals(argumentCaptor.getValue().unitPrice(), body.getUnitPrice());
        assertEquals(argumentCaptor.getValue().unitType(), body.getUnitType());
    }

    @Test
    void shouldReturnAProductResponseOnSuccess(){
        Product product = ProductMock.get();
        when(updateProductUsecaseSpy.handle(any())).thenReturn(product);
        ProductResponse response = sut.handle(UUID.randomUUID().toString(), UpdateProductRequestMock.get());
        assertEquals(response, new ProductResponse(product));
    }

}
