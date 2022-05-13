package br.edu.unifei.inventory.application.db.usecase;

import br.edu.unifei.inventory.application.contract.FindProductByIdUsecase;
import br.edu.unifei.inventory.application.db.repository.GetProductRepository;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DbFindProductByIdUsecase implements FindProductByIdUsecase {
    private final GetProductRepository getProductRepository;

    @Override
    public Product handle(UUID productId) throws ProductNotFoundException {
        return getProductRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
    }
}
