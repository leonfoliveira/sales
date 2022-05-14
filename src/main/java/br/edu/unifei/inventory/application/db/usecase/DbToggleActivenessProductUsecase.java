package br.edu.unifei.inventory.application.db.usecase;

import br.edu.unifei.authentication.domain.exception.UserNotFoundException;
import br.edu.unifei.inventory.application.contract.FindProductByIdUsecase;
import br.edu.unifei.inventory.application.contract.ToggleActivenessProductUsecase;
import br.edu.unifei.inventory.application.db.repository.UpdateProductRepository;
import br.edu.unifei.inventory.domain.entity.Product;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DbToggleActivenessProductUsecase implements ToggleActivenessProductUsecase {
    private final FindProductByIdUsecase findProductByIdUsecase;
    private final UpdateProductRepository updateProductRepository;

    @Override
    public void handle(UUID productId) throws UserNotFoundException {
        Product product = findProductByIdUsecase.handle(productId);

        product.setIsActive(!product.getIsActive());
        updateProductRepository.update(product);
    }
}
