package br.edu.unifei.transaction.application.db.usecase;

import br.edu.unifei.transaction.application.db.repository.GetSaleRepository;
import br.edu.unifei.transaction.application.db.repository.GetSaleRepositorySpy;
import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DbGetAllSaleUseCaseTest {
    GetSaleRepository getSaleRepositorySpy;
    DbGetAllSaleUseCase sut;

    @BeforeEach
    void setup() {
        getSaleRepositorySpy = GetSaleRepositorySpy.get();
        sut = new DbGetAllSaleUseCase(getSaleRepositorySpy);
    }

    @Test
    void shouldReturnAListOfSalesOnSuccess() {
        List<Sale> sales = List.of(SaleMock.get());
        when(getSaleRepositorySpy.getAll())
                .thenReturn(sales);
        List<Sale> result = sut.handle();
        assertEquals(result, sales);
    }

    @Test
    void shouldReturnAEmptyListOfSalesOnSuccess() {
        when(getSaleRepositorySpy.getAll())
                .thenReturn(List.of());
        List<Sale> result = sut.handle();
        assertEquals(result.size(), 0);
    }

}
