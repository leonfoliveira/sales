package br.edu.unifei.transaction.application.contract;

import br.edu.unifei.transaction.domain.entity.SaleMock;

import java.util.UUID;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public abstract class FindSaleByIdUseCaseSpy {
    public static FindSaleByIdUsecase get() {
        FindSaleByIdUsecase findSaleByIdUsecase = spy(FindSaleByIdUsecase.class);
        when(findSaleByIdUsecase.handle(UUID.randomUUID()))
                .thenReturn(SaleMock.get());
        return findSaleByIdUsecase;
    }
}
