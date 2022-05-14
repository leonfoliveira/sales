package br.edu.unifei.authentication.application.contract;

import br.edu.unifei.authentication.domain.entity.UserMock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public abstract class CreateUserUsecaseSpy {
    public static CreateUserUsecase get() {
        CreateUserUsecase createUserUsecaseSpy = spy(CreateUserUsecase.class);

        when(createUserUsecaseSpy.handle(any()))
                .thenReturn(UserMock.get());

        return createUserUsecaseSpy;
    }
}
