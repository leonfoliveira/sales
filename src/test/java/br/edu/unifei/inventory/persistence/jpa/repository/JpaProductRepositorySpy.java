package br.edu.unifei.inventory.persistence.jpa.repository;

import br.edu.unifei.inventory.persistence.jpa.entity.JpaProductMock;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public abstract class JpaProductRepositorySpy {
    public static JpaProductRepository get() {
        JpaProductRepository jpaProductRepository = spy(JpaProductRepository.class);

        when(jpaProductRepository.findByIsActive(any()))
                .thenReturn(List.of(JpaProductMock.get()));
        when(jpaProductRepository.findById(any()))
                .thenReturn(Optional.of(JpaProductMock.get()));
        when(jpaProductRepository.findByBarCode(any()))
                .thenReturn(Optional.of(JpaProductMock.get()));

        return jpaProductRepository;
    }
}
