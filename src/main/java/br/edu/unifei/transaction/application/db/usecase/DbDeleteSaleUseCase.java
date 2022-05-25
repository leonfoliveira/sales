package br.edu.unifei.transaction.application.db.usecase;

import br.edu.unifei.transaction.application.contract.DeleteSaleUsecase;
import br.edu.unifei.transaction.application.db.repository.DeleteSaleRepository;
import br.edu.unifei.transaction.application.db.repository.GetSaleRepository;
import br.edu.unifei.transaction.domain.exception.SaleNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DbDeleteSaleUseCase implements DeleteSaleUsecase {
    private final DeleteSaleRepository deleteSaleRepository;
    private final GetSaleRepository getSaleRepository;

    @Override
    public void handle(UUID saleId) throws SaleNotFoundException {
        if(getSaleRepository.findById(saleId).isPresent()) {
            deleteSaleRepository.delete(saleId);
        } else {
            throw new SaleNotFoundException();
        }
    }
}
