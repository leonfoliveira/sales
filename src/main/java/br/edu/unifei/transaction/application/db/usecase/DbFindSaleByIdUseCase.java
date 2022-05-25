package br.edu.unifei.transaction.application.db.usecase;

import br.edu.unifei.transaction.application.contract.FindSaleByIdUsecase;
import br.edu.unifei.transaction.application.db.repository.GetSaleRepository;
import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.exception.SaleNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DbFindSaleByIdUseCase implements FindSaleByIdUsecase {
    private final GetSaleRepository getSaleRepository;

    @Override
    public Sale handle(UUID saleId) throws SaleNotFoundException {
        return getSaleRepository.findById(saleId)
                .orElseThrow(SaleNotFoundException::new);
    }
}
