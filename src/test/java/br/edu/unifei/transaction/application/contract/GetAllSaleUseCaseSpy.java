package br.edu.unifei.transaction.application.contract;

import br.edu.unifei.transaction.domain.entity.SaleMock;

import java.util.List;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public abstract class GetAllSaleUseCaseSpy {
    public static GetAllSaleUsecase get() {
        GetAllSaleUsecase getAllSaleUsecase = spy(GetAllSaleUsecase.class);
        when(getAllSaleUsecase.handle())
                .thenReturn(List.of(SaleMock.get()));
        return getAllSaleUsecase;
    }
}
