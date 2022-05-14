package br.edu.unifei.inventory.application.db.usecase;

import br.edu.unifei.inventory.application.contract.FindProductByIdUsecase;
import br.edu.unifei.inventory.application.contract.FindProductByIdUsecaseSpy;
import br.edu.unifei.inventory.application.db.repository.GetProductRepository;
import br.edu.unifei.inventory.application.db.repository.GetProductRepositorySpy;
import br.edu.unifei.inventory.application.db.repository.UpdateProductRepository;
import br.edu.unifei.inventory.application.db.repository.UpdateProductRepositorySpy;
import br.edu.unifei.inventory.application.dto.UpdateProductDTO;
import br.edu.unifei.inventory.application.dto.UpdateProductDTOMock;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.entity.ProductMock;
import br.edu.unifei.inventory.domain.exception.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DbUpdateProductUsecaseTest {
    GetProductRepository getProductRepositorySpy;
    FindProductByIdUsecase findProductByIdUsecaseSpy;
    UpdateProductRepository updateProductRepositorySpy;
    DbUpdateProductUsecase sut;

    @BeforeEach
    void setup() {
        getProductRepositorySpy = GetProductRepositorySpy.get();
        when(getProductRepositorySpy.findByBarCode(any()))
                .thenReturn(Optional.empty());
        findProductByIdUsecaseSpy = FindProductByIdUsecaseSpy.get();
        updateProductRepositorySpy = UpdateProductRepositorySpy.get();
        sut = new DbUpdateProductUsecase(
                getProductRepositorySpy,
                findProductByIdUsecaseSpy,
                updateProductRepositorySpy);
    }

    @Test
    void shouldCallGetProductRepositoryWithCorrectParam() {
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        UpdateProductDTO dto = UpdateProductDTOMock.get();
        sut.handle(dto);
        verify(getProductRepositorySpy).findByBarCode(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue(), dto.barCode());
    }

    @Test
    void shouldThrowProductNotFoundExceptionIfGetProductRepositoryReturnsSomeProductWithADifferentId() {
        when(getProductRepositorySpy.findByBarCode(any()))
                .thenReturn(Optional.of(ProductMock.get()));
        assertThrows(ProductNotFoundException.class,
                () -> sut.handle(UpdateProductDTOMock.get()));
    }

    @Test
    void shouldCallFindProductByIdUsecaseUsecaseWithCorrectParams() {
        UpdateProductDTO dto = UpdateProductDTOMock.get();
        sut.handle(dto);
        verify(findProductByIdUsecaseSpy).handle(dto.id());
    }

    @Test
    void shouldCallUpdateUserRepositoryWithCorrectParams() {
        UpdateProductDTO dto = UpdateProductDTOMock.get();
        Product product = ProductMock.get();
        when(findProductByIdUsecaseSpy.handle(any()))
                .thenReturn(product);
        ArgumentCaptor<Product> argumentCaptor = ArgumentCaptor.forClass(Product.class);
        sut.handle(dto);
        verify(updateProductRepositorySpy).update(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().getId(), product.getId());
        assertEquals(argumentCaptor.getValue().getTitle(), dto.title());
        assertEquals(argumentCaptor.getValue().getBarCode(), dto.barCode());
        assertEquals(argumentCaptor.getValue().getUnitPrice(), new BigDecimal(dto.unitPrice().toString()));
        assertEquals(argumentCaptor.getValue().getUnitType(), dto.unitType());
        assertEquals(argumentCaptor.getValue().getIsActive(), product.getIsActive());
    }
}
