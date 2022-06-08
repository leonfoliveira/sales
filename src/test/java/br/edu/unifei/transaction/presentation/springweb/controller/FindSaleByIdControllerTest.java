package br.edu.unifei.transaction.presentation.springweb.controller;

import br.edu.unifei.transaction.application.contract.FindSaleByIdUseCaseSpy;
import br.edu.unifei.transaction.application.contract.FindSaleByIdUsecase;
import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleMock;
import br.edu.unifei.transaction.presentation.springweb.response.SaleResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FindSaleByIdControllerTest {
    FindSaleByIdUsecase findSaleByIdUsecase;
    FindSaleByIdController sut;

    @BeforeEach
    void setup() {
        findSaleByIdUsecase = FindSaleByIdUseCaseSpy.get();
        sut = new FindSaleByIdController(findSaleByIdUsecase);
    }

    @Test
    void shouldCallFindSaleByIdUseCaseWithCorrectParams() {
        ArgumentCaptor<UUID> argumentCaptor = ArgumentCaptor.forClass(UUID.class);
        UUID saleId = UUID.randomUUID();
        when(findSaleByIdUsecase.handle(any()))
                .thenReturn(SaleMock.get());
        sut.handle(saleId.toString());
        verify(findSaleByIdUsecase).handle(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().toString(), saleId.toString());
    }

    @Test
    void shouldReturnASaleResponseOnSuccess() {
        Sale sale = SaleMock.get();
        when(findSaleByIdUsecase.handle(any()))
                .thenReturn(sale);
        SaleResponse response = sut.handle(UUID.randomUUID().toString());
        assertEquals(response, new SaleResponse(sale));
    }

}
