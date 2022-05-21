package br.edu.unifei.inventory.persistence.jpa.adapter;

import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.entity.ProductMock;
import br.edu.unifei.inventory.persistence.jpa.entity.JpaProduct;
import br.edu.unifei.inventory.persistence.jpa.repository.JpaProductRepository;
import br.edu.unifei.inventory.persistence.jpa.repository.JpaProductRepositorySpy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class JpaSaveProductRepositoryAdapterTest {
    JpaProductRepository jpaProductRepositorySpy;
    JpaSaveProductRepositoryAdapter sut;

    @BeforeEach
    void setup() {
        jpaProductRepositorySpy = JpaProductRepositorySpy.get();
        sut = new JpaSaveProductRepositoryAdapter(jpaProductRepositorySpy);
    }

    @Test
    void insertShouldCallJpaProductRepositoryWithCorrectParams() {
        ArgumentCaptor<JpaProduct> argumentCaptor = ArgumentCaptor.forClass(JpaProduct.class);
        Product product = ProductMock.get();
        sut.insert(product);
        verify(jpaProductRepositorySpy).save(argumentCaptor.capture());
        JpaProduct jpaProduct = argumentCaptor.getValue();
        assertEquals(jpaProduct.toDomainEntity(), product);
        assertNotNull(jpaProduct.getCreatedAt());
    }

    @Test
    void updateShouldCallJpaProductRepositoryWithCorrectParams() {
        Product oldProduct = ProductMock.get();
        JpaProduct oldJpaProduct = new JpaProduct(oldProduct);
        oldJpaProduct.setCreatedAt(LocalDateTime.now());
        when(jpaProductRepositorySpy.findById(any()))
                .thenReturn(Optional.of(oldJpaProduct));
        ArgumentCaptor<JpaProduct> argumentCaptor = ArgumentCaptor.forClass(JpaProduct.class);
        Product product = ProductMock.get();
        sut.update(product);
        verify(jpaProductRepositorySpy).save(argumentCaptor.capture());
        JpaProduct jpaProduct = argumentCaptor.getValue();
        assertEquals(jpaProduct.toDomainEntity(), product);
        assertNotNull(jpaProduct.getCreatedAt());
        assertNotNull(jpaProduct.getUpdatedAt());
    }
}
