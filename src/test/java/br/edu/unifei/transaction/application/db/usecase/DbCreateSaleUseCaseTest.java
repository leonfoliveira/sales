package br.edu.unifei.transaction.application.db.usecase;

import br.edu.unifei.common.exception.BusinessRuleException;
import br.edu.unifei.inventory.application.contract.FindProductByIdUsecase;
import br.edu.unifei.inventory.application.contract.FindProductByIdUsecaseSpy;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.entity.ProductMock;
import br.edu.unifei.inventory.domain.entity.UnitType;
import br.edu.unifei.inventory.domain.exception.ProductNotFoundException;
import br.edu.unifei.transaction.application.db.repository.InsertSaleRepository;
import br.edu.unifei.transaction.application.db.repository.InsertSaleRepositorySpy;
import br.edu.unifei.transaction.application.dto.CreateSaleDTO;
import br.edu.unifei.transaction.application.dto.CreateSaleDTOMock;
import br.edu.unifei.transaction.application.dto.CreateSaleItemDTO;
import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleItem;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DbCreateSaleUseCaseTest {
    FindProductByIdUsecase findProductByIdUsecaseSpy;
    InsertSaleRepository insertSaleRepositorySpy;
    DbCreateSaleUseCase sut;
    Faker faker = new Faker();

    @BeforeEach
    void setup() {
        findProductByIdUsecaseSpy = FindProductByIdUsecaseSpy.get();
        insertSaleRepositorySpy = InsertSaleRepositorySpy.get();
        sut = new DbCreateSaleUseCase(findProductByIdUsecaseSpy, insertSaleRepositorySpy);
    }

    @Test
    void shouldCallGetProductRepositoryWithCorrectParam() {
        CreateSaleDTO dto = CreateSaleDTOMock.get();
        Product product = ProductMock.get();
        product.setIsActive(true);
        when(findProductByIdUsecaseSpy.handle(any()))
                .thenReturn(product);
        sut.handle(dto);
        verify(findProductByIdUsecaseSpy, atLeast(1))
                .handle(dto.items().get(0).productId());
    }

    @Test
    void shouldThrowProductNotFoundExceptionIfProductIsNotPresent() {
        CreateSaleDTO dto = CreateSaleDTOMock.get();
        when(findProductByIdUsecaseSpy.handle(any()))
                .thenThrow(ProductNotFoundException.class);
        assertThrows(ProductNotFoundException.class,
                () -> sut.handle(dto));
    }

    @Test
    void shouldThrowProductNotFoundExceptionIfProductIsNotActive() {
        CreateSaleDTO dto = CreateSaleDTOMock.get();
        Product product = ProductMock.get();
        product.setIsActive(false);
        when(findProductByIdUsecaseSpy.handle(any()))
                .thenReturn(product);
        assertThrows(ProductNotFoundException.class,
                () -> sut.handle(dto));
    }

    @Test
    void shouldThrowBusinessRuleExceptionIfProductAmountIsNotIntegerAndUnitTypeIsUNIT() {
        CreateSaleDTO dto = CreateSaleDTOMock.get();
        CreateSaleItemDTO saleItemDTO = new CreateSaleItemDTO(
                UUID.randomUUID(),
                faker.random().nextDouble(),
                faker.random().nextDouble());
        Product product = ProductMock.get();
        product.setUnitType(UnitType.UNIT);
        dto.items().add(saleItemDTO);
        when(findProductByIdUsecaseSpy.handle(any()))
                .thenReturn(product);
        assertThrows(BusinessRuleException.class,
                () -> sut.handle(dto));
    }

    @Test
    void shouldCallInsertSaleRepositoryWithCorrectParam() {
        ArgumentCaptor<Sale> argumentCaptor = ArgumentCaptor.forClass(Sale.class);
        CreateSaleDTO dto = CreateSaleDTOMock.get();
        Product product = ProductMock.get();
        product.setIsActive(true);
        when(findProductByIdUsecaseSpy.handle(any()))
                .thenReturn(product);
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
        when(findProductByIdUsecaseSpy.handle(any()))
                .thenReturn(product);
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
