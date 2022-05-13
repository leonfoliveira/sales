package br.edu.unifei.transaction.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Sale {
    private UUID id;
    private List<SaleItem> items;
    private LocalDateTime createdAt;
}
