package br.edu.unifei.inventory.presentation.springweb.response;

import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.entity.UnitType;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(UUID id, String title, String barCode, BigDecimal unitPrice, UnitType unitType, Boolean isActive) {
    public ProductResponse(Product product){
        this(
                product.getId(),
                product.getTitle(),
                product.getBarCode(),
                product.getUnitPrice(),
                product.getUnitType(),
                product.getIsActive()
        );
    }
}
