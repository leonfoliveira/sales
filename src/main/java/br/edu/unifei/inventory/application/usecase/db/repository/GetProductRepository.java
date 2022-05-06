package br.edu.unifei.inventory.application.usecase.db.repository;

import br.edu.unifei.inventory.domain.entity.Product;

import java.util.List;

public interface GetProductRepository {
    List<Product> getAll(Boolean includeInactive);
}
