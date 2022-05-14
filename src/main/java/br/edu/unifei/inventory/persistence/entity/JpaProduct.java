package br.edu.unifei.inventory.persistence.entity;

import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.entity.UnitType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class JpaProduct {
    @Id
    private UUID id;
    private String title;
    private String barCode;
    private Double unitPrice;
    private UnitType unitType;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public JpaProduct() {
    }

    public JpaProduct(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.barCode = product.getBarCode();
        this.unitPrice = product.getUnitPrice().doubleValue();
        this.unitType = product.getUnitType();
        this.isActive = product.getIsActive();
    }

    public Product toDomainEntity() {
        return Product.builder()
                .id(this.id)
                .title(this.title)
                .barCode(this.barCode)
                .unitPrice(new BigDecimal(this.unitPrice.toString()))
                .unitType(this.unitType)
                .isActive(this.isActive)
                .build();
    }
}
