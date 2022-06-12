package br.edu.unifei.transaction.application.db.usecase;

import br.edu.unifei.transaction.application.contract.DeleteSaleUsecase;
import br.edu.unifei.transaction.application.contract.FindSaleByIdUsecase;
import br.edu.unifei.transaction.application.db.repository.DeleteSaleRepository;
import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.exception.SaleNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DbDeleteSaleUseCase implements DeleteSaleUsecase {
    private final FindSaleByIdUsecase findSaleByIdUsecase;
    private final DeleteSaleRepository deleteSaleRepository;

    @Override
    public void handle(UUID saleId) throws SaleNotFoundException {
        Sale sale = findSaleByIdUsecase.handle(saleId);
        deleteSaleRepository.delete(sale);
    }
}
