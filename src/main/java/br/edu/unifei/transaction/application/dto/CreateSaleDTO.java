package br.edu.unifei.transaction.application.dto;

import java.util.List;

public record CreateSaleDTO(List<CreateSaleItemDTO> items) {
}
