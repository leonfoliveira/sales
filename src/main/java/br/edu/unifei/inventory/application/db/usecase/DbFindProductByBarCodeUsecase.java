package br.edu.unifei.inventory.application.db.usecase;

import br.edu.unifei.inventory.application.contract.FindProductByBarCodeUsecase;
import br.edu.unifei.inventory.application.db.repository.GetProductRepository;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DbFindProductByBarCodeUsecase implements FindProductByBarCodeUsecase {
    private final GetProductRepository getProductRepository;

    @Override
    public Product handle(String barCode) throws ProductNotFoundException {
        return getProductRepository.findByBarCode(barCode)
                .orElseThrow(ProductNotFoundException::new);
    }
}
