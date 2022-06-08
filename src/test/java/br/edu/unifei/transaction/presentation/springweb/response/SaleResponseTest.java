package br.edu.unifei.transaction.presentation.springweb.response;

import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SaleResponseTest {
    @Test
    void shouldMapFromSaleEntityCorrectly() {
        Sale sale = SaleMock.get();
        SaleResponse saleResponse = new SaleResponse(sale);
        assertEquals(saleResponse.id(), sale.getId());
        assertEquals(saleResponse.items(), sale.getItems());
        assertEquals(saleResponse.createdAt(), sale.getCreatedAt());
    }
}