package br.edu.unifei.authentication.application.contract;

import br.edu.unifei.authentication.application.model.AuthorizationMock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public abstract class LoginUserUsecaseSpy {
    public static LoginUserUsecase get() {
        LoginUserUsecase loginUserUsecase = spy(LoginUserUsecase.class);

        when(loginUserUsecase.handle(any(), any()))
                .thenReturn(AuthorizationMock.get());

        return loginUserUsecase;
    }
}
