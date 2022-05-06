package br.edu.unifei.transaction.domain.entity;

import br.edu.unifei.inventory.domain.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SaleItem {
    private Product product;
    private Double amount;
    private BigDecimal unitPrice;
}
