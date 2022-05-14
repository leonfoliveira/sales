package br.edu.unifei.inventory.persistence.adapter;

import br.edu.unifei.inventory.application.db.repository.InsertProductRepository;
import br.edu.unifei.inventory.application.db.repository.UpdateProductRepository;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.exception.ProductNotFoundException;
import br.edu.unifei.inventory.persistence.entity.JpaProduct;
import br.edu.unifei.inventory.persistence.repository.JpaProductRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class JpaSaveProductRepositoryAdapter implements InsertProductRepository, UpdateProductRepository {
    private final JpaProductRepository repository;

    @Override
    public void insert(Product product) {
        JpaProduct jpaProduct = new JpaProduct(product);
        jpaProduct.setCreatedAt(LocalDateTime.now());
        repository.save(jpaProduct);
    }

    @Override
    public void update(Product product) {
        JpaProduct existingProduct = repository.findById(product.getId())
                .orElseThrow(ProductNotFoundException::new);
        JpaProduct jpaProduct = new JpaProduct(product);
        jpaProduct.setCreatedAt(existingProduct.getCreatedAt());
        jpaProduct.setUpdatedAt(LocalDateTime.now());
        repository.save(jpaProduct);
    }
}
