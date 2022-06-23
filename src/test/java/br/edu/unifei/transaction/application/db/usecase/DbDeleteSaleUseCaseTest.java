package br.edu.unifei.transaction.application.db.usecase;

import br.edu.unifei.transaction.application.contract.FindSaleByIdUseCaseSpy;
import br.edu.unifei.transaction.application.contract.FindSaleByIdUsecase;
import br.edu.unifei.transaction.application.db.repository.DeleteSaleRepository;
import br.edu.unifei.transaction.application.db.repository.DeleteSaleRepositorySpy;
import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DbDeleteSaleUseCaseTest {
    FindSaleByIdUsecase findSaleByIdUsecaseSpy;
    DeleteSaleRepository deleteSaleRepositorySpy;
    DbDeleteSaleUseCase sut;

    @BeforeEach
    void setup() {
        findSaleByIdUsecaseSpy = FindSaleByIdUseCaseSpy.get();
        deleteSaleRepositorySpy = DeleteSaleRepositorySpy.get();
        sut = new DbDeleteSaleUseCase(findSaleByIdUsecaseSpy, deleteSaleRepositorySpy);
    }

    @Test
    void shouldCallGetSaleRepositoryWithCorrectParams() {
        UUID uuid = UUID.randomUUID();
        sut.handle(uuid);
        verify(findSaleByIdUsecaseSpy).handle(uuid);
    }

    @Test
    void shouldCallDeleteSaleRepositoryWithCorrectParam() {
        ArgumentCaptor<Sale> argumentCaptor = ArgumentCaptor.forClass(Sale.class);
        Sale sale = SaleMock.get();
        when(findSaleByIdUsecaseSpy.handle(any()))
                .thenReturn(sale);
        sut.handle(sale.getId());
        verify(deleteSaleRepositorySpy).delete(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().getId(), sale.getId());
    }
}
