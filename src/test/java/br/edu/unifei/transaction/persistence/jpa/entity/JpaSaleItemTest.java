package br.edu.unifei.transaction.persistence.jpa.entity;

import br.edu.unifei.transaction.domain.entity.SaleItem;
import br.edu.unifei.transaction.domain.entity.SaleItemMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JpaSaleItemTest {
    @Test
    void shouldMapCreateAJpaEntityCorrectly() {
        SaleItem saleItem = SaleItemMock.get();
        JpaSaleItem jpaSale = new JpaSaleItem(saleItem);
        assertEquals(jpaSale.getProduct().toDomainEntity(), saleItem.getProduct());
        assertEquals(jpaSale.getAmount(), saleItem.getAmount());
        assertEquals(jpaSale.getUnitPrice(), saleItem.getUnitPrice());
    }

    @Test
    void shouldMapToADomainEntityCorrectly() {
        SaleItem saleItem = SaleItemMock.get();
        JpaSaleItem jpaSaleItem = new JpaSaleItem(saleItem);
        assertEquals(jpaSaleItem.toDomainEntity(), saleItem);
    }
}
