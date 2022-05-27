package br.edu.unifei.transaction.persistence.jpa.entity;

import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleItem;
import br.edu.unifei.transaction.persistence.jpa.repository.JpaSaleItemRepository;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class JpaSale {
    @Id
    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;

    public JpaSale() {
    }

    public JpaSale (Sale sale) {
        this.id = sale.getId();
        this.createdAt = sale.getCreatedAt();
    }

    public Sale toDomainEntity(List<SaleItem> items) {
        return Sale.builder()
                .id(this.id)
                .items(items)
                .createdAt(this.createdAt)
                .build();
    }

}
