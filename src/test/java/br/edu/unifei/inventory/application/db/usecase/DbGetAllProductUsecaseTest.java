package br.edu.unifei.inventory.application.db.usecase;

import br.edu.unifei.inventory.application.db.repository.GetProductRepository;
import br.edu.unifei.inventory.application.db.repository.GetProductRepositorySpy;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.entity.ProductMock;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DbGetAllProductUsecaseTest {
    GetProductRepository getProductRepositorySpy;
    DbGetAllProductUsecase sut;
    Faker faker = new Faker();

    @BeforeEach
    void setup() {
        getProductRepositorySpy = GetProductRepositorySpy.get();
        sut = new DbGetAllProductUsecase(getProductRepositorySpy);
    }

    @Test
    void shouldCallGetProductRepositoryWithCorrectParams() {
        Boolean includeInactive = faker.random().nextBoolean();
        sut.handle(includeInactive);
        verify(getProductRepositorySpy).getAll(includeInactive);
    }

    @Test
    void shouldCallGetProductRepositoryWithDefaultParam() {
        sut.handle();
        verify(getProductRepositorySpy).getAll(true);
    }

    @Test
    void shouldReturnAnEmptyListIfGetProductRepositoryReturnsNoUser() {
        when(getProductRepositorySpy.getAll(any()))
                .thenReturn(List.of());
        List<Product> products = sut.handle();
        assertEquals(products.size(), 0);
    }

    @Test
    void shouldReturnAListOfProductsOnSuccess() {
        List<Product> products = List.of(ProductMock.get());
        when(getProductRepositorySpy.getAll(any()))
                .thenReturn(products);
        List<Product> result = sut.handle();
        assertEquals(result, products);
    }
}
