package br.edu.unifei.transaction.persistence.jpa.adapter;

import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleMock;
import br.edu.unifei.transaction.persistence.jpa.entity.JpaSale;
import br.edu.unifei.transaction.persistence.jpa.repository.JpaSaleRepository;
import br.edu.unifei.transaction.persistence.jpa.repository.JpaSaleRepositorySpy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class JpaGetSaleRepositoryAdapterTest {
    JpaSaleRepository jpaSaleRepositorySpy;
    JpaGetSaleRepositoryAdapter sut;

    @BeforeEach
    void setup() {
        jpaSaleRepositorySpy = JpaSaleRepositorySpy.get();
        sut = new JpaGetSaleRepositoryAdapter(jpaSaleRepositorySpy);
    }

    @Test
    void getAllShouldCallJpaSaleRepositoryWithCorrectParams() {
        sut.getAll();
        verify(jpaSaleRepositorySpy).findByDeletedAtIsNull();
    }

    @Test
    void getAllShouldReturnJpaSaleRepositoryMappedResponse() {
        Sale sale = SaleMock.get();
        when(jpaSaleRepositorySpy.findByDeletedAtIsNull())
                .thenReturn(List.of(new JpaSale(sale)));
        List<Sale> result = sut.getAll();
        assertEquals(result, List.of(sale));
    }

    @Test
    void findByIdShouldCallJpaSaleRepositoryWithCorrectParams() {
        UUID saleId = UUID.randomUUID();
        sut.findById(saleId);
        verify(jpaSaleRepositorySpy).findById(saleId);
    }

    @Test
    void findByIdShouldReturnJpaSaleRepositoryMappedResponse() {
        Sale sale = SaleMock.get();
        when(jpaSaleRepositorySpy.findById(any()))
                .thenReturn(Optional.of(new JpaSale(sale)));
        Optional<Sale> result = sut.findById(UUID.randomUUID());
        assertEquals(result, Optional.of(sale));
    }

}
