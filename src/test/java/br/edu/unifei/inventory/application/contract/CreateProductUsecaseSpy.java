package br.edu.unifei.inventory.application.contract;

import br.edu.unifei.inventory.domain.entity.ProductMock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public abstract class CreateProductUsecaseSpy {
    public static CreateProductUsecase get(){
        CreateProductUsecase createProductUsecase = spy(CreateProductUsecase.class);
        when(createProductUsecase.handle(any())).thenReturn(ProductMock.get());
        return  createProductUsecase;
    }
}
