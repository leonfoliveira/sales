package br.edu.unifei.authentication.application.contract;

import br.edu.unifei.authentication.domain.entity.UserMock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public abstract class UpdateUserUsecaseSpy {
    public static UpdateUserUsecase get() {
        UpdateUserUsecase updateUserUsecase = spy(UpdateUserUsecase.class);

        when(updateUserUsecase.handle(any()))
                .thenReturn(UserMock.get());

        return updateUserUsecase;
    }
}
