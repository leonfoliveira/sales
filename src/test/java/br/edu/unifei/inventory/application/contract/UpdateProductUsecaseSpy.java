package br.edu.unifei.inventory.application.contract;

import br.edu.unifei.inventory.domain.entity.ProductMock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public abstract class UpdateProductUsecaseSpy {
    public static UpdateProductUsecase get() {
        UpdateProductUsecase updateProductUsecase = spy(UpdateProductUsecase.class);
        when(updateProductUsecase.handle(any())).thenReturn(ProductMock.get());
        return updateProductUsecase;
    }

}
