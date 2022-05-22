package br.edu.unifei.transaction.application.db.usecase;

import br.edu.unifei.transaction.application.contract.GetAllSaleUsecase;
import br.edu.unifei.transaction.application.db.repository.GetSaleRepository;
import br.edu.unifei.transaction.domain.entity.Sale;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DbGetAllSaleUseCase implements GetAllSaleUsecase {
    private final GetSaleRepository getSaleRepository;

    @Override
    public List<Sale> handle() {
        return getSaleRepository.getAll();
    }

}
