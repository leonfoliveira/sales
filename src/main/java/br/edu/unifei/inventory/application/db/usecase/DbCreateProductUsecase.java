package br.edu.unifei.inventory.application.db.usecase;

import br.edu.unifei.inventory.application.contract.CreateProductUsecase;
import br.edu.unifei.inventory.application.db.repository.GetProductRepository;
import br.edu.unifei.inventory.application.db.repository.InsertProductRepository;
import br.edu.unifei.inventory.application.dto.CreateProductDTO;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.exception.BarCodeInUseException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class DbCreateProductUsecase implements CreateProductUsecase {
    private final GetProductRepository getProductRepository;
    private final InsertProductRepository insertProductRepository;

    @Override
    public Product handle(CreateProductDTO dto) throws BarCodeInUseException {
        Optional<Product> productWithBarCode = getProductRepository.findByBarCode(dto.barCode());
        if (productWithBarCode.isPresent()) {
            throw new BarCodeInUseException();
        }

        Product product = Product.builder()
                .id(UUID.randomUUID())
                .title(dto.title())
                .barCode(dto.barCode())
                .unitPrice(new BigDecimal(dto.unitPrice().toString()))
                .unitType(dto.unitType())
                .isActive(true)
                .build();
        insertProductRepository.insert(product);
        
        return product;
    }
}
