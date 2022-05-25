package br.edu.unifei.inventory.presentation.springweb.response;

import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.entity.ProductMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductResponseTest {
    @Test
    void shouldMapFromProductEntityCorrectly() {
        Product product = ProductMock.get();
        ProductResponse productResponse = new ProductResponse(product);
        assertEquals(productResponse.id(), product.getId());
        assertEquals(productResponse.title(), product.getTitle());
        assertEquals(productResponse.barCode(), product.getBarCode());
        assertEquals(productResponse.unitPrice(), product.getUnitPrice());

        assertEquals(productResponse.unitType(), product.getUnitType());
    }
}
