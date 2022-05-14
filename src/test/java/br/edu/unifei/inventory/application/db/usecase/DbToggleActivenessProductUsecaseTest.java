package br.edu.unifei.inventory.application.db.usecase;

import br.edu.unifei.inventory.application.contract.FindProductByIdUsecase;
import br.edu.unifei.inventory.application.contract.FindProductByIdUsecaseSpy;
import br.edu.unifei.inventory.application.db.repository.UpdateProductRepository;
import br.edu.unifei.inventory.application.db.repository.UpdateProductRepositorySpy;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.entity.ProductMock;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DbToggleActivenessProductUsecaseTest {
    FindProductByIdUsecase findProductByIdUsecaseSpy;
    UpdateProductRepository updateProductRepositorySpy;
    DbToggleActivenessProductUsecase sut;
    Faker faker = new Faker();

    @BeforeEach
    void setup() {
        findProductByIdUsecaseSpy = FindProductByIdUsecaseSpy.get();
        updateProductRepositorySpy = UpdateProductRepositorySpy.get();
        sut = new DbToggleActivenessProductUsecase(findProductByIdUsecaseSpy, updateProductRepositorySpy);
    }

    @Test
    void shouldCallFindProductByIdUsecaseWithCorrectParam() {
        UUID productId = UUID.randomUUID();
        sut.handle(productId);
        verify(findProductByIdUsecaseSpy).handle(productId);
    }

    @Test
    void shouldCallUpdateProductRepositoryWithCorrectParams() {
        Product product = ProductMock.get();
        Boolean isActive = faker.random().nextBoolean();
        product.setIsActive(isActive);
        when(findProductByIdUsecaseSpy.handle(any()))
                .thenReturn(product);
        ArgumentCaptor<Product> argumentCaptor = ArgumentCaptor.forClass(Product.class);
        sut.handle(UUID.randomUUID());
        verify(updateProductRepositorySpy).update(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().getIsActive(), !isActive);
    }
}
