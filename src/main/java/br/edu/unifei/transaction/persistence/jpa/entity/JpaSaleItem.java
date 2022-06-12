package br.edu.unifei.transaction.persistence.jpa.entity;

import br.edu.unifei.inventory.persistence.jpa.entity.JpaProduct;
import br.edu.unifei.transaction.domain.entity.SaleItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "SaleItem")
@Table(name = "tb_sale_item")
public class JpaSaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private JpaProduct product;
    private double amount;
    private BigDecimal unitPrice;

    public JpaSaleItem() {
    }

    public JpaSaleItem(SaleItem saleItem) {
        this.product = new JpaProduct(saleItem.getProduct());
        this.amount = saleItem.getAmount();
        this.unitPrice = saleItem.getUnitPrice();
    }

    public SaleItem toDomainEntity() {
        return SaleItem.builder()
                .product(product.toDomainEntity())
                .amount(this.amount)
                .unitPrice(this.unitPrice)
                .build();
    }
}
