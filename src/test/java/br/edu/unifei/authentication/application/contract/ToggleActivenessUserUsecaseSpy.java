package br.edu.unifei.authentication.application.contract;

import static org.mockito.Mockito.spy;

public abstract class ToggleActivenessUserUsecaseSpy {
    public static ToggleActivenessUserUsecase get() {
        return spy(ToggleActivenessUserUsecase.class);
    }
}
