package br.edu.unifei.transaction.persistence.jpa.repository;

import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.transaction.domain.entity.SaleItem;
import br.edu.unifei.transaction.persistence.jpa.entity.JpaSaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaSaleItemRepository extends JpaRepository<JpaSaleItem, Long> {
}
