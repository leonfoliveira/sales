package br.edu.unifei.transaction.application.contract;

import br.edu.unifei.transaction.domain.entity.Sale;

import java.util.List;

public interface GetAllSaleUsecase {
    List<Sale> handle();
}
