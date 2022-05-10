package br.edu.unifei.transaction.application.db.repository;

import br.edu.unifei.transaction.domain.entity.Sale;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GetSaleRepository {
    List<Sale> getAll();

    Optional<Sale> findById(UUID id);
}
