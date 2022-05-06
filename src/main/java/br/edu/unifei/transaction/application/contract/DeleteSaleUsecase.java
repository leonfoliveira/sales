package br.edu.unifei.transaction.application.contract;

import br.edu.unifei.transaction.domain.exception.SaleNotFoundException;

import java.util.UUID;

public interface DeleteSaleUsecase {
    void handle(UUID saleId) throws SaleNotFoundException;
}
