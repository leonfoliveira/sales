package br.edu.unifei.inventory.persistence.jpa.repository;

import br.edu.unifei.inventory.persistence.jpa.entity.JpaProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaProductRepository extends JpaRepository<JpaProduct, UUID> {
    List<JpaProduct> findByIsActive(Boolean isActive);

    Optional<JpaProduct> findByBarCode(String barCode);
}
