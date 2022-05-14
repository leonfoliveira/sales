package br.edu.unifei.inventory.persistence.adapter;

import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.entity.ProductMock;
import br.edu.unifei.inventory.persistence.entity.JpaProduct;
import br.edu.unifei.inventory.persistence.repository.JpaProductRepository;
import br.edu.unifei.inventory.persistence.repository.JpaProductRepositorySpy;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class JpaGetProductRepositoryAdapterTest {
    JpaProductRepository jpaProductRepositorySpy;
    JpaGetProductRepositoryAdapter sut;
    Faker faker = new Faker();

    @BeforeEach
    void setup() {
        jpaProductRepositorySpy = JpaProductRepositorySpy.get();
        sut = new JpaGetProductRepositoryAdapter(jpaProductRepositorySpy);
    }

    @Test
    void getAllShouldCallJpaProductRepositoryWithCorrectParams() {
        Boolean includeInactive = faker.random().nextBoolean();
        sut.getAll(includeInactive);
        verify(jpaProductRepositorySpy).findByIsActive(includeInactive);
    }

    @Test
    void getAllShouldReturnJpaProductRepositoryMappedResponse() {
        Product product = ProductMock.get();
        when(jpaProductRepositorySpy.findByIsActive(any()))
                .thenReturn(List.of(new JpaProduct(product)));
        List<Product> result = sut.getAll(faker.random().nextBoolean());
        assertEquals(result, List.of(product));
    }

    @Test
    void findByIdShouldCallJpaProductRepositoryWithCorrectParams() {
        UUID productId = UUID.randomUUID();
        sut.findById(productId);
        verify(jpaProductRepositorySpy).findById(productId);
    }

    @Test
    void findByIdShouldReturnJpaProductRepositoryMappedResponse() {
        Product product = ProductMock.get();
        when(jpaProductRepositorySpy.findById(any()))
                .thenReturn(Optional.of(new JpaProduct(product)));
        Optional<Product> result = sut.findById(UUID.randomUUID());
        assertEquals(result, Optional.of(product));
    }

    @Test
    void findByLoginShouldCallJpaProductRepositoryWithCorrectParams() {
        String barCode = faker.number().digits(10);
        sut.findByBarCode(barCode);
        verify(jpaProductRepositorySpy).findByBarCode(barCode);
    }

    @Test
    void findByLoginShouldReturnJpaProductRepositoryMappedResponse() {
        Product product = ProductMock.get();
        when(jpaProductRepositorySpy.findByBarCode(any()))
                .thenReturn(Optional.of(new JpaProduct(product)));
        Optional<Product> result = sut.findByBarCode(faker.number().digits(10));
        assertEquals(result, Optional.of(product));
    }
}
