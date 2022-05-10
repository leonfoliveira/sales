package br.edu.unifei.transaction.application.db.repository;

import br.edu.unifei.transaction.domain.entity.Sale;

import java.util.List;

public interface GetSaleRepository {
    List<Sale> getAll();
}
