package br.edu.unifei.transaction.application.db.usecase;

import br.edu.unifei.transaction.application.db.repository.DeleteSaleRepository;
import br.edu.unifei.transaction.application.db.repository.DeleteSaleRepositorySpy;
import br.edu.unifei.transaction.application.db.repository.GetSaleRepository;
import br.edu.unifei.transaction.application.db.repository.GetSaleRepositorySpy;
import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleMock;
import br.edu.unifei.transaction.domain.exception.SaleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DbDeleteSaleUseCaseTest {
    GetSaleRepository getSaleRepositorySpy;
    DeleteSaleRepository deleteSaleRepositorySpy;
    DbDeleteSaleUseCase sut;

    @BeforeEach
    void setup() {
        getSaleRepositorySpy = GetSaleRepositorySpy.get();
        deleteSaleRepositorySpy = DeleteSaleRepositorySpy.get();
        sut = new DbDeleteSaleUseCase(deleteSaleRepositorySpy, getSaleRepositorySpy);
    }

    @Test
    void shouldCallGetSaleRepositoryWithCorrectParams() {
        UUID uuid = UUID.randomUUID();
        sut.handle(uuid);
        verify(getSaleRepositorySpy).findById(uuid);
    }

    @Test
    void shouldThrowSaleNotFoundExceptionIfSaleIsNotPresent() {
        Sale sale = SaleMock.get();
        when(getSaleRepositorySpy.findById(any()))
                .thenReturn(Optional.empty());
        assertThrows(SaleNotFoundException.class,
                () -> sut.handle(sale.getId()));
    }

    @Test
    void shouldCallDeleteSaleRepositoryWithCorrectParam() {
        ArgumentCaptor<UUID> argumentCaptor = ArgumentCaptor.forClass(UUID.class);
        Sale sale = SaleMock.get();
        when(getSaleRepositorySpy.findById(any()))
                .thenReturn(Optional.of(sale));
        sut.handle(sale.getId());
        verify(deleteSaleRepositorySpy).delete(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue(), sale.getId());
    }

}
