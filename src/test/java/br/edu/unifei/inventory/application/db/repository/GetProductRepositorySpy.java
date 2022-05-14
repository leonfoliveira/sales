package br.edu.unifei.inventory.application.db.repository;

import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.entity.ProductMock;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public abstract class GetProductRepositorySpy {
    public static GetProductRepository get() {
        GetProductRepository getProductRepository = spy(GetProductRepository.class);

        Product activeProduct = ProductMock.get();
        activeProduct.setIsActive(true);
        when(getProductRepository.getAll(true))
                .thenReturn(List.of(activeProduct));
        Product inactiveProduct = ProductMock.get();
        activeProduct.setIsActive(false);
        when(getProductRepository.getAll(false))
                .thenReturn(List.of(inactiveProduct));
        when(getProductRepository.findById(any()))
                .thenReturn(Optional.of(ProductMock.get()));
        when(getProductRepository.findByBarCode(any()))
                .thenReturn(Optional.of(ProductMock.get()));

        return getProductRepository;
    }
}
