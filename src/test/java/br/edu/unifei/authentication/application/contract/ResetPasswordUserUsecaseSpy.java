package br.edu.unifei.authentication.application.contract;

import static org.mockito.Mockito.spy;

public abstract class ResetPasswordUserUsecaseSpy {
    public static ResetPasswordUserUsecase get() {
        return spy(ResetPasswordUserUsecase.class);
    }
}
