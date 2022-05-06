package br.edu.unifei.inventory.application.contract;

import br.edu.unifei.inventory.domain.entity.Product;

import java.util.List;

public interface GetAllProductUsecase {
    List<Product> handle();
    List<Product> handle(Boolean includeInactive);
}
