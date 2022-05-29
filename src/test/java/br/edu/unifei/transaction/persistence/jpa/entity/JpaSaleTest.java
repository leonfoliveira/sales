package br.edu.unifei.transaction.persistence.jpa.entity;

import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JpaSaleTest {
    @Test
    void shouldMapCreateAJpaEntityCorrectly() {
        Sale sale = SaleMock.get();
        JpaSale jpaSale = new JpaSale(sale);
        assertEquals(jpaSale.getId(), sale.getId());
        assertEquals(jpaSale.getJpaSaleItem()
                .stream()
                .map(JpaSaleItem::toDomainEntity)
                .toList(),
                sale.getItems());
        assertEquals(jpaSale.getCreatedAt(), sale.getCreatedAt());
    }

    @Test
    void shouldMapToADomainEntityCorrectly() {
        Sale sale = SaleMock.get();
        JpaSale jpaSale = new JpaSale(sale);
        assertEquals(jpaSale.toDomainEntity(), sale);
    }
}
