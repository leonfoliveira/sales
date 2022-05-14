package br.edu.unifei.inventory.application.db.repository;

import br.edu.unifei.inventory.domain.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GetProductRepository {
    List<Product> getAll(Boolean includeInactive);

    Optional<Product> findById(UUID productId);

    Optional<Product> findByBarCode(String barCode);
}
