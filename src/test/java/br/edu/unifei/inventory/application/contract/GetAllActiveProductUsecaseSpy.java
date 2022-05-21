package br.edu.unifei.inventory.application.contract;

import br.edu.unifei.inventory.domain.entity.ProductMock;

import java.util.List;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


public class GetAllActiveProductUsecaseSpy {
    public static  GetAllProductUsecase get() {
        GetAllProductUsecase getAllProductUsecase = spy(GetAllProductUsecase.class);
        when(getAllProductUsecase.handle(false)).thenReturn(List.of(ProductMock.get()));
        return getAllProductUsecase;
    }

}
