package br.edu.unifei.transaction.application.db.usecase;

import br.edu.unifei.transaction.application.db.repository.GetSaleRepository;
import br.edu.unifei.transaction.application.db.repository.GetSaleRepositorySpy;
import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleMock;
import br.edu.unifei.transaction.domain.exception.SaleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DbFindSaleByIdUseCaseTest {
    GetSaleRepository getSaleRepositorySpy;
    DbFindSaleByIdUseCase sut;

    @BeforeEach
    void setup() {
        getSaleRepositorySpy = GetSaleRepositorySpy.get();
        sut = new DbFindSaleByIdUseCase(getSaleRepositorySpy);
    }

    @Test
    void shouldCallGetSaleRepositoryWithCorrectParams() {
        UUID uuid = UUID.randomUUID();
        sut.handle(uuid);
        verify(getSaleRepositorySpy).findById(uuid);
    }

    @Test
    void shouldReturnSaleNotFoundExceptionIfGetSaleRepositoryReturnsEmptyResponse() {
        when(getSaleRepositorySpy.findById(any()))
                .thenReturn(Optional.empty());
        assertThrows(SaleNotFoundException.class,
                () -> sut.handle(UUID.randomUUID()));
    }

    @Test
    void shouldReturnASaleEntityOnSuccess() {
        Sale sale = SaleMock.get();
        when(getSaleRepositorySpy.findById(any()))
                .thenReturn(Optional.of(sale));
        Sale result = sut.handle(UUID.randomUUID());
        assertEquals(result, sale);
    }

}
