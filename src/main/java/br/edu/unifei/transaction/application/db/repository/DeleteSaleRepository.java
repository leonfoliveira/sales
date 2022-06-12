package br.edu.unifei.transaction.application.db.repository;

import br.edu.unifei.transaction.domain.entity.Sale;

public interface DeleteSaleRepository {
    void delete(Sale sale);
}
