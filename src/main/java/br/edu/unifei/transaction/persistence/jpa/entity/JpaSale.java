package br.edu.unifei.transaction.persistence.jpa.entity;

import br.edu.unifei.transaction.domain.entity.Sale;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "Sale")
@Table(name = "tb_sale")
public class JpaSale {
    @Id
    private UUID id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<JpaSaleItem> jpaSaleItem;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;

    public JpaSale() {
    }

    public JpaSale(Sale sale) {
        this.id = sale.getId();
        this.jpaSaleItem = sale.getItems()
                .stream()
                .map(JpaSaleItem::new)
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
