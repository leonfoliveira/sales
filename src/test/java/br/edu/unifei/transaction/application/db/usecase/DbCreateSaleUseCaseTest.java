package br.edu.unifei.transaction.application.db.usecase;

import br.edu.unifei.inventory.application.db.repository.GetProductRepository;
import br.edu.unifei.inventory.application.db.repository.GetProductRepositorySpy;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.entity.ProductMock;
import br.edu.unifei.inventory.domain.exception.ProductNotFoundException;
import br.edu.unifei.transaction.application.db.repository.InsertSaleRepository;
import br.edu.unifei.transaction.application.db.repository.InsertSaleRepositorySpy;
import br.edu.unifei.transaction.application.dto.CreateSaleDTO;
import br.edu.unifei.transaction.application.dto.CreateSaleDTOMock;
import br.edu.unifei.transaction.application.dto.CreateSaleItemDTO;
import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DbCreateSaleUseCaseTest {
    GetProductRepository getProductRepositorySpy;
    InsertSaleRepository insertSaleRepositorySpy;
    DbCreateSaleUseCase sut;

    @BeforeEach
    void setup() {
        getProductRepositorySpy = GetProductRepositorySpy.get();
        insertSaleRepositorySpy = InsertSaleRepositorySpy.get();
        sut = new DbCreateSaleUseCase(getProductRepositorySpy, insertSaleRepositorySpy);
    }

    @Test
    void shouldCallGetProductRepositoryWithCorrectParam() {
        CreateSaleDTO dto = CreateSaleDTOMock.get();
        Product product = ProductMock.get();
        product.setIsActive(true);
        when(getProductRepositorySpy.findById(any()))
                .thenReturn(Optional.of(product));
        sut.handle(dto);
        verify(getProductRepositorySpy, atLeast(1))
                .findById(dto.items().get(0).productId());
    }

    @Test
    void shouldThrowProductNotFoundExceptionIfProductIsNotPresent() {
        CreateSaleDTO dto = CreateSaleDTOMock.get();
        when(getProductRepositorySpy.findById(any()))
                .thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class,
                () -> sut.handle(dto));
    }

    @Test
    void shouldThrowProductNotFoundExceptionIfProductIsNotActive() {
        CreateSaleDTO dto = CreateSaleDTOMock.get();
        Product product = ProductMock.get();
        product.setIsActive(false);
        when(getProductRepositorySpy.findById(any()))
                .thenReturn(Optional.of(product));
        assertThrows(ProductNotFoundException.class,
                () -> sut.handle(dto));
    }

    @Test
    void shouldCallInsertSaleRepositoryWithCorrectParam() {
        ArgumentCaptor<Sale> argumentCaptor = ArgumentCaptor.forClass(Sale.class);
        CreateSaleDTO dto = CreateSaleDTOMock.get();
        Product product = ProductMock.get();
        product.setIsActive(true);
        when(getProductRepositorySpy.findById(any()))
                .thenReturn(Optional.of(product));
        sut.handle(dto);
        verify(insertSaleRepositorySpy).insert(argumentCaptor.capture());
        List<SaleItem> items = convertCreateSaleItemDTOToSaleItem(product, dto);
        assertNotNull(argumentCaptor.getValue().getId());
        assertEquals(argumentCaptor.getValue().getItems(), items);
        assertNotNull(argumentCaptor.getValue().getCreatedAt());
    }

    @Test
    void shouldReturnASaleInstanceOnSuccess() {
        CreateSaleDTO dto = CreateSaleDTOMock.get();
        Product product = ProductMock.get();
        product.setIsActive(true);
        when(getProductRepositorySpy.findById(any()))
                .thenReturn(Optional.of(product));
        Sale result = sut.handle(dto);
        List<SaleItem> items = convertCreateSaleItemDTOToSaleItem(product, dto);
        assertNotNull(result.getId());
        assertEquals(result.getItems(), items);
        assertNotNull(result.getCreatedAt());
    }

    List<SaleItem> convertCreateSaleItemDTOToSaleItem(Product product, CreateSaleDTO createSaleDTO) {
        List<SaleItem> items = createSaleDTO.items().stream()
                .map(item -> SaleItem.builder()
                        .product(product)
                        .amount(item.amount())
                        .unitPrice(new BigDecimal(item.unitPrice().toString()))
                        .build())
                .collect(Collectors.toList());
        return items;
    }

}
