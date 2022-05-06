package br.edu.unifei.inventory.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class Product {
    private UUID id;
    private String title;
    private String barCode;
    private BigDecimal unitPrice;
    private UnitType unitType;
    private Boolean isActive;
}
