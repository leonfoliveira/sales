package br.edu.unifei.inventory.application.usecase.db.repository;

import br.edu.unifei.inventory.domain.entity.Product;

public interface UpdateProductRepository {
    void update(Product product);
}
