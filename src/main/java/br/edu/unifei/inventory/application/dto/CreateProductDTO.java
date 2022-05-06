package br.edu.unifei.inventory.application.dto;

import br.edu.unifei.inventory.domain.entity.UnitType;

public record CreateProductDTO(String title, String barCode, Double unitPrice, UnitType unitType) {
}
