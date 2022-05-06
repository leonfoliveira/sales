package br.edu.unifei.transaction.application.usecase.db.repository;

import java.util.UUID;

public interface DeleteSaleRepository {
    void delete(UUID id);
}
