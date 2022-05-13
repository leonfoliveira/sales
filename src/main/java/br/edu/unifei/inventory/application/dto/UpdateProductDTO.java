package br.edu.unifei.inventory.application.dto;

import br.edu.unifei.inventory.domain.entity.UnitType;

import java.util.UUID;

public record UpdateProductDTO(UUID id, String title, String barCode, Double unitPrice, UnitType unitType) {
}
