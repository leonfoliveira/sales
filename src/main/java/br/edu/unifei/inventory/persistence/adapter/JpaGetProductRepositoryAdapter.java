package br.edu.unifei.inventory.persistence.adapter;

import br.edu.unifei.inventory.application.db.repository.GetProductRepository;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.persistence.entity.JpaProduct;
import br.edu.unifei.inventory.persistence.repository.JpaProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class JpaGetProductRepositoryAdapter implements GetProductRepository {
    private final JpaProductRepository repository;

    @Override
    public List<Product> getAll(Boolean includeInactive) {
        return repository.findByIsActive(includeInactive)
                .stream()
                .map(JpaProduct::toDomainEntity)
                .toList();
    }

    @Override
    public Optional<Product> findById(UUID productId) {
        return repository.findById(productId)
                .map(JpaProduct::toDomainEntity);
    }

    @Override
    public Optional<Product> findByBarCode(String barCode) {
        return repository.findByBarCode(barCode)
                .map(JpaProduct::toDomainEntity);
    }
}
