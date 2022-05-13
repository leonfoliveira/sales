package br.edu.unifei.inventory.application.contract;

import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.exception.ProductNotFoundException;

import java.util.UUID;

public interface FindProductByIdUsecase {
    Product handle(UUID productId) throws ProductNotFoundException;
}
