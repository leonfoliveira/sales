package br.edu.unifei.authentication.application.contract;

import static org.mockito.Mockito.spy;

public abstract class SetPasswordUserUsecaseSpy {
    public static SetPasswordUserUsecase get() {
        return spy(SetPasswordUserUsecase.class);
    }
}
