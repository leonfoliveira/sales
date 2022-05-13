package br.edu.unifei.inventory.application.db.usecase;

import br.edu.unifei.inventory.application.db.repository.GetProductRepository;
import br.edu.unifei.inventory.application.db.repository.GetProductRepositorySpy;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.entity.ProductMock;
import br.edu.unifei.inventory.domain.exception.ProductNotFoundException;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DbFindProductByBarCodeUsecaseTest {
    GetProductRepository getProductRepositorySpy;
    DbFindProductByBarCodeCodeUsecase sut;
    Faker faker = new Faker();

    @BeforeEach
    void setup() {
        getProductRepositorySpy = GetProductRepositorySpy.get();
        sut = new DbFindProductByBarCodeCodeUsecase(getProductRepositorySpy);
    }

    @Test
    void shouldCallGetProductRepositoryWithCorrectParams() {
        String barcode = faker.number().digits(10);
        sut.handle(barcode);
        verify(getProductRepositorySpy).findByBarCode(barcode);
    }

    @Test
    void shouldThrowProductNotFoundExceptionIfGetProductRepositoryReturnsEmptyResponse() {
        when(getProductRepositorySpy.findByBarCode(any()))
                .thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class,
                () -> sut.handle(faker.number().digits(10)));
    }

    @Test
    void shouldReturnAProductEntityOnSuccess() {
        Product product = ProductMock.get();
        when(getProductRepositorySpy.findByBarCode(any()))
                .thenReturn(Optional.of(product));
        Product result = sut.handle(faker.number().digits(10));
        assertEquals(result, product);
    }
}
