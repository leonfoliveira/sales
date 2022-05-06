package br.edu.unifei.transaction.application.contract;

import br.edu.unifei.inventory.domain.exception.ProductNotFoundException;
import br.edu.unifei.transaction.application.dto.CreateSaleDTO;
import br.edu.unifei.transaction.domain.entity.Sale;

public interface CreateSaleUsecase {
    Sale handle(CreateSaleDTO dto) throws ProductNotFoundException;
}
