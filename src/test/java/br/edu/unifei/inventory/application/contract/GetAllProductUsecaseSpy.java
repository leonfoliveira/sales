package br.edu.unifei.inventory.application.contract;

import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.entity.ProductMock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


public class GetAllProductUsecaseSpy {
    public static  GetAllProductUsecase get() {
        GetAllProductUsecase getAllProductUsecase = spy(GetAllProductUsecase.class);
        when(getAllProductUsecase.handle()).thenReturn(List.of(ProductMock.get()));
        return getAllProductUsecase;
    }

}
