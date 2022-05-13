package br.edu.unifei.transaction.application.dto;

import java.util.UUID;

public record CreateSaleItemDTO(UUID productId, Double amount, Double unitPrice) {
}
