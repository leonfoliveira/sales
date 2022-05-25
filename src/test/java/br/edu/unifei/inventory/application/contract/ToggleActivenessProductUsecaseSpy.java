package br.edu.unifei.inventory.application.contract;

import static org.mockito.Mockito.spy;

public class ToggleActivenessProductUsecaseSpy {
    public static ToggleActivenessProductUsecase get() {
        ToggleActivenessProductUsecase toggleActivenessProductUsecase = spy(ToggleActivenessProductUsecase.class);
        return  toggleActivenessProductUsecase;
    }
}
