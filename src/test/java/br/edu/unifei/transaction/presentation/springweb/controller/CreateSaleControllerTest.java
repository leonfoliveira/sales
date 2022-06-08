package br.edu.unifei.transaction.presentation.springweb.controller;

import br.edu.unifei.transaction.application.contract.CreateSaleUseCaseSpy;
import br.edu.unifei.transaction.application.contract.CreateSaleUsecase;
import br.edu.unifei.transaction.application.dto.CreateSaleDTO;
import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleMock;
import br.edu.unifei.transaction.presentation.springweb.request.CreateSaleRequest;
import br.edu.unifei.transaction.presentation.springweb.request.CreateSaleRequestMock;
import br.edu.unifei.transaction.presentation.springweb.response.SaleResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateSaleControllerTest {
    CreateSaleUsecase createSaleUsecaseSpy;
    CreateSaleController sut;

    @BeforeEach
    void setup() {
        createSaleUsecaseSpy = CreateSaleUseCaseSpy.get();
        sut = new CreateSaleController(createSaleUsecaseSpy);
    }

    @Test
    void shouldCallCreateSaleWithCorrectParams() {
        ArgumentCaptor<CreateSaleDTO> argumentCaptor = ArgumentCaptor.forClass(CreateSaleDTO.class);
        CreateSaleRequest body = CreateSaleRequestMock.get();
        sut.handle(body);
        verify(createSaleUsecaseSpy).handle(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().items(), body.getItems());
    }

    @Test
    void shouldReturnASaleResponseOnSuccess() {
        Sale sale = SaleMock.get();
        when(createSaleUsecaseSpy.handle(any()))
                .thenReturn(sale);
        SaleResponse response = sut.handle(CreateSaleRequestMock.get());
        assertEquals(response, new SaleResponse(sale));
    }

}