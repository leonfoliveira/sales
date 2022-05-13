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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DbFindProductByIdUsecaseTest {
    GetProductRepository getProductRepositorySpy;
    DbFindProductByIdUsecase sut;
    Faker faker = new Faker();

    @BeforeEach
    void setup() {
        getProductRepositorySpy = GetProductRepositorySpy.get();
        sut = new DbFindProductByIdUsecase(getProductRepositorySpy);
    }

    @Test
    void shouldCallGetProductRepositoryWithCorrectParams() {
        UUID id = UUID.randomUUID();
        sut.handle(id);
        verify(getProductRepositorySpy).findById(id);
    }

    @Test
    void shouldThrowProductNotFoundExceptionIfGetProductRepositoryReturnsEmptyResponse() {
        when(getProductRepositorySpy.findById(any()))
                .thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class,
                () -> sut.handle(UUID.randomUUID()));
    }

    @Test
    void shouldReturnAProductEntityOnSuccess() {
        Product product = ProductMock.get();
        when(getProductRepositorySpy.findById(any()))
                .thenReturn(Optional.of(product));
        Product result = sut.handle(UUID.randomUUID());
        assertEquals(result, product);
    }
}
