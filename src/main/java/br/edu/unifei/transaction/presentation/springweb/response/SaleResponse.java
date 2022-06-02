package br.edu.unifei.transaction.presentation.springweb.response;

import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleItem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record SaleResponse(UUID id, List<SaleItem> items, LocalDateTime createdAt) {
    public SaleResponse(Sale sale) {
        this(
                sale.getId(),
                sale.getItems(),
                sale.getCreatedAt()
        );
    }
}
