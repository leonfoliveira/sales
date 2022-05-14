package br.edu.unifei.inventory.persistence.jpa.entity;

import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.entity.ProductMock;
import br.edu.unifei.inventory.persistence.jpa.entity.JpaProduct;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JpaProductTest {
    @Test
    void shouldMapCreateAJpaEntityCorrectly() {
        Product product = ProductMock.get();
        JpaProduct jpaProduct = new JpaProduct(product);
        assertEquals(jpaProduct.getId(), product.getId());
        assertEquals(jpaProduct.getTitle(), product.getTitle());
        assertEquals(jpaProduct.getBarCode(), product.getBarCode());
        assertEquals(jpaProduct.getUnitPrice(), product.getUnitPrice().doubleValue());
        assertEquals(jpaProduct.getUnitType(), product.getUnitType());
        assertEquals(jpaProduct.getIsActive(), product.getIsActive());
    }

    @Test
    void shouldMapToADomainEntityCorrectly() {
        Product product = ProductMock.get();
        JpaProduct jpaProduct = new JpaProduct(product);
        assertEquals(jpaProduct.toDomainEntity(), product);
    }
}
