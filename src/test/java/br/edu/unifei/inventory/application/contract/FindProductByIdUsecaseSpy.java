package br.edu.unifei.inventory.application.contract;

import br.edu.unifei.inventory.domain.entity.ProductMock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public abstract class FindProductByIdUsecaseSpy {
    public static FindProductByIdUsecase get() {
        FindProductByIdUsecase findProductByIdUsecase = spy(FindProductByIdUsecase.class);

        when(findProductByIdUsecase.handle(any()))
                .thenReturn(ProductMock.get());

        return findProductByIdUsecase;
    }
}
