package br.edu.unifei.inventory.application.db.usecase;

import br.edu.unifei.inventory.application.db.repository.GetProductRepository;
import br.edu.unifei.inventory.application.db.repository.GetProductRepositorySpy;
import br.edu.unifei.inventory.application.db.repository.InsertProductRepository;
import br.edu.unifei.inventory.application.db.repository.InsertProductRepositorySpy;
import br.edu.unifei.inventory.application.dto.CreateProductDTO;
import br.edu.unifei.inventory.application.dto.CreateProductDTOMock;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.entity.ProductMock;
import br.edu.unifei.inventory.domain.exception.BarCodeInUseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DbCreateProductUsecaseTest {
    GetProductRepository getProductRepositorySpy;
    InsertProductRepository insertProductRepositorySpy;
    DbCreateProductUsecase sut;

    @BeforeEach
    void setup() {
        getProductRepositorySpy = GetProductRepositorySpy.get();
        when(getProductRepositorySpy.findByBarCode(any()))
                .thenReturn(Optional.empty());
        insertProductRepositorySpy = InsertProductRepositorySpy.get();
        sut = new DbCreateProductUsecase(getProductRepositorySpy, insertProductRepositorySpy);
    }

    @Test
    void shouldCallGetProductRepositoryWithCorrectParam() {
        CreateProductDTO dto = CreateProductDTOMock.get();
        sut.handle(dto);
        verify(getProductRepositorySpy).findByBarCode(dto.barCode());
    }

    @Test
    void shouldThrowBarCodeInUseExceptionIfGetProductRepositoryReturnsAProduct() {
        when(getProductRepositorySpy.findByBarCode(any()))
                .thenReturn(Optional.of(ProductMock.get()));
        assertThrows(BarCodeInUseException.class,
                () -> sut.handle(CreateProductDTOMock.get()));
    }

    @Test
    void shouldCallInsertProductRepositoryWithCorrectParam() {
        ArgumentCaptor<Product> argumentCaptor = ArgumentCaptor.forClass(Product.class);
        CreateProductDTO dto = CreateProductDTOMock.get();
        sut.handle(dto);
        verify(insertProductRepositorySpy).insert(argumentCaptor.capture());
        assertNotNull(argumentCaptor.getValue().getId());
        assertEquals(argumentCaptor.getValue().getTitle(), dto.title());
        assertEquals(argumentCaptor.getValue().getBarCode(), dto.barCode());
        assertEquals(argumentCaptor.getValue().getUnitPrice(), new BigDecimal(dto.unitPrice().toString()));
        assertEquals(argumentCaptor.getValue().getUnitType(), dto.unitType());
        assertEquals(argumentCaptor.getValue().getIsActive(), true);
    }

    @Test
    void shouldReturnAProductInstanceOnSuccess() {
        CreateProductDTO dto = CreateProductDTOMock.get();
        Product result = sut.handle(dto);
        assertNotNull(result.getId());
        assertEquals(result.getTitle(), dto.title());
        assertEquals(result.getBarCode(), dto.barCode());
        assertEquals(result.getUnitPrice(), new BigDecimal(dto.unitPrice().toString()));
        assertEquals(result.getUnitType(), dto.unitType());
        assertEquals(result.getIsActive(), true);
    }
}
