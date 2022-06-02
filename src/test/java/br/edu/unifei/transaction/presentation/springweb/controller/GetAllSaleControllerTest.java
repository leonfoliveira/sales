package br.edu.unifei.transaction.presentation.springweb.controller;

import br.edu.unifei.transaction.application.contract.GetAllSaleUseCaseSpy;
import br.edu.unifei.transaction.application.contract.GetAllSaleUsecase;
import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleMock;
import br.edu.unifei.transaction.presentation.springweb.response.SaleResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAllSaleControllerTest {
    GetAllSaleUsecase getAllSaleUsecase;
    GetAllSaleController sut;

    @BeforeEach
    void setup() {
        getAllSaleUsecase = GetAllSaleUseCaseSpy.get();
        sut = new GetAllSaleController(getAllSaleUsecase);
    }

    @Test
    void shouldCallGetAllSaleUseCase() {
        sut.handle();
        verify(getAllSaleUsecase).handle();
    }

    @Test
    void shouldReturnAListOfSaleResponseOnSuccess() {
        List<Sale> sales = List.of(SaleMock.get());
        when(getAllSaleUsecase.handle()).thenReturn(sales);
        List<SaleResponse> responses = sut.handle();
        assertEquals(responses, sales.stream().map(SaleResponse::new).toList());
    }

}
