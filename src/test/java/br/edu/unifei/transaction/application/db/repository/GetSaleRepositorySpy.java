package br.edu.unifei.transaction.application.db.repository;

import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleMock;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public abstract class GetSaleRepositorySpy {
    public static GetSaleRepository get() {
        GetSaleRepository getSaleRepository = spy(GetSaleRepository.class);

        Sale sale = SaleMock.get();

        when(getSaleRepository.getAll())
                .thenReturn(List.of(sale));
        when(getSaleRepository.findById(any()))
                .thenReturn(Optional.of(sale));

        return getSaleRepository;
    }
}
