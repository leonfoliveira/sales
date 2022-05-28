package br.edu.unifei.transaction.persistence.jpa.entity;

import br.edu.unifei.transaction.domain.entity.Sale;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_sale")
public class JpaSale {
    @Id
    private UUID id;
    @OneToMany
    private List<JpaSaleItem> jpaSaleItem;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;

    public JpaSale() {
    }

    public JpaSale (Sale sale) {
        this.id = sale.getId();
        this.jpaSaleItem = sale.getItems()
                .stream()
                .map(item -> new JpaSaleItem(item))
                .toList();
        this.createdAt = sale.getCreatedAt();
    }

    public Sale toDomainEntity() {
        return Sale.builder()
                .id(this.id)
                .items(jpaSaleItem
                        .stream()
                        .map(JpaSaleItem::toDomainEntity)
                        .toList())
                .createdAt(this.createdAt)
                .build();
    }

}
