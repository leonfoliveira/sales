package br.edu.unifei.transaction.application.contract;

import br.edu.unifei.transaction.domain.entity.SaleMock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public abstract class CreateSaleUseCaseSpy {
    public static CreateSaleUsecase get() {
        CreateSaleUsecase createSaleUsecase = spy(CreateSaleUsecase.class);
        when(createSaleUsecase.handle(any()))
                .thenReturn(SaleMock.get());
        return createSaleUsecase;
    }
}
