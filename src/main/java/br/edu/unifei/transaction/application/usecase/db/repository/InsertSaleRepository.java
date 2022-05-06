package br.edu.unifei.transaction.application.usecase.db.repository;

import br.edu.unifei.transaction.domain.entity.Sale;

public interface InsertSaleRepository {
    void insert(Sale sale);
}
