package br.edu.unifei.transaction.persistence.jpa.repository;

import br.edu.unifei.transaction.persistence.jpa.entity.JpaSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaSaleRepository extends JpaRepository<JpaSale, UUID> {
    List<JpaSale> findByDeletedAtIsNull();
}
