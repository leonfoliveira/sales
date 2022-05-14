package br.edu.unifei.inventory.application.db.usecase;

import br.edu.unifei.inventory.application.contract.FindProductByIdUsecase;
import br.edu.unifei.inventory.application.contract.UpdateProductUsecase;
import br.edu.unifei.inventory.application.db.repository.GetProductRepository;
import br.edu.unifei.inventory.application.db.repository.UpdateProductRepository;
import br.edu.unifei.inventory.application.dto.UpdateProductDTO;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.exception.BarCodeInUseException;
import br.edu.unifei.inventory.domain.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
public class DbUpdateProductUsecase implements UpdateProductUsecase {
    private final GetProductRepository getProductRepository;
    private final FindProductByIdUsecase findProductByIdUsecase;
    private final UpdateProductRepository updateProductRepository;

    @Override
    public Product handle(UpdateProductDTO dto) throws ProductNotFoundException, BarCodeInUseException {
        Product product = findProductByIdUsecase.handle(dto.id());

        Optional<Product> existingProduct = getProductRepository.findByBarCode(dto.barCode());
        if (existingProduct.isPresent() && !existingProduct.get().getId().equals(dto.id())) {
            throw new ProductNotFoundException();
        }

        product.setTitle(dto.title());
        product.setBarCode(dto.barCode());
        product.setUnitPrice(new BigDecimal(dto.unitPrice().toString()));
        product.setUnitType(dto.unitType());

        updateProductRepository.update(product);
        return product;
    }
}
