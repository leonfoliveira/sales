package br.edu.unifei.transaction.persistence.jpa.entity;

import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleItem;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
public class JpaSaleItem {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private UUID productId;
    private double amount;
    private BigDecimal unitPrice;
    private UUID saleId;

    public JpaSaleItem() {
    }

    public JpaSaleItem(SaleItem saleItem, UUID saleId) {
        this.productId = saleItem.getProduct().getId();
        this.amount = saleItem.getAmount();
        this.unitPrice = saleItem.getUnitPrice();
        this.saleId = saleId;
    }

    public SaleItem toDomainEntity(Product product) {
        return SaleItem.builder()
                .product(product)
                .amount(this.amount)
                .unitPrice(this.unitPrice)
                .build();
    }

}
