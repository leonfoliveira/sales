package br.edu.unifei.transaction.persistence.jpa.adapter;

import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleMock;
import br.edu.unifei.transaction.domain.exception.SaleNotFoundException;
import br.edu.unifei.transaction.persistence.jpa.entity.JpaSale;
import br.edu.unifei.transaction.persistence.jpa.entity.JpaSaleItem;
import br.edu.unifei.transaction.persistence.jpa.repository.JpaSaleRepository;
import br.edu.unifei.transaction.persistence.jpa.repository.JpaSaleRepositorySpy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class JpaSaveSaleRepositoryAdapterTest {
    JpaSaleRepository jpaSaleRepositorySpy;
    JpaSaveSaleRepositoryAdapter sut;

    @BeforeEach
    void setup() {
        jpaSaleRepositorySpy = JpaSaleRepositorySpy.get();
        sut = new JpaSaveSaleRepositoryAdapter(jpaSaleRepositorySpy);
    }

    @Test
    void insertShouldCallJpaSaleRepositoryWithCorrectParams() {
        ArgumentCaptor<JpaSale> argumentCaptor = ArgumentCaptor.forClass(JpaSale.class);
        Sale sale = SaleMock.get();
        sut.insert(sale);
        verify(jpaSaleRepositorySpy).save(argumentCaptor.capture());
        JpaSale jpaSale = argumentCaptor.getValue();
        assertEquals(jpaSale.getId(), sale.getId());
        assertEquals(jpaSale.getJpaSaleItem()
                        .stream()
                        .map(JpaSaleItem::toDomainEntity)
                        .toList(),
                sale.getItems());
        assertEquals(jpaSale.getCreatedAt(), sale.getCreatedAt());
    }

    @Test
    void deleteShouldCallJpaSaleRepositoryWithCorrectParams() {
        ArgumentCaptor<JpaSale> argumentCaptor = ArgumentCaptor.forClass(JpaSale.class);
        Sale sale = SaleMock.get();
        when(jpaSaleRepositorySpy.findById(any()))
                .thenReturn(Optional.of(new JpaSale(sale)));
        sut.delete(sale);
        verify(jpaSaleRepositorySpy).save(argumentCaptor.capture());
        JpaSale jpaSale = argumentCaptor.getValue();
        assertEquals(jpaSale.getId(), sale.getId());
        assertEquals(jpaSale.getJpaSaleItem()
                        .stream()
                        .map(JpaSaleItem::toDomainEntity)
                        .toList(),
                sale.getItems());
        assertEquals(jpaSale.getCreatedAt(), sale.getCreatedAt());
        assertNotNull(jpaSale.getDeletedAt());
    }

    @Test
    void deleteShouldThrowSaleNotFoundExceptionIfSaleDoNotExist() {
        when(jpaSaleRepositorySpy.findById(any()))
                .thenReturn(Optional.empty());
        assertThrows(SaleNotFoundException.class,
                () -> sut.delete(SaleMock.get()));
    }
}
