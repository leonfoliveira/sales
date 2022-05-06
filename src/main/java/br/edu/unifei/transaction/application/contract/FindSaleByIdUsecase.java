package br.edu.unifei.transaction.application.contract;

import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.exception.SaleNotFoundException;

import java.util.UUID;

public interface FindSaleByIdUsecase {
    Sale handle(UUID saleId) throws SaleNotFoundException;
}
