package br.edu.unifei.transaction.presentation.springweb.controller;

import br.edu.unifei.transaction.application.contract.DeleteSaleUseCaseSpy;
import br.edu.unifei.transaction.application.contract.DeleteSaleUsecase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class DeleteSaleControllerTest {
    DeleteSaleUsecase deleteSaleUsecase;
    DeleteSaleController sut;

    @BeforeEach
    void setup() {
        deleteSaleUsecase = DeleteSaleUseCaseSpy.get();
        sut = new DeleteSaleController(deleteSaleUsecase);
    }

    @Test
    void shouldCallDeleteSaleUseCaseWithCorrectParams() {
        ArgumentCaptor<UUID> argumentCaptor = ArgumentCaptor.forClass(UUID.class);
        UUID saleId = UUID.randomUUID();
        sut.handle(saleId.toString());
        verify(deleteSaleUsecase).handle(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().toString(), saleId.toString());
    }

}
