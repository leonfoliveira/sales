package br.edu.unifei.authentication.application.contract;

import br.edu.unifei.authentication.domain.entity.UserMock;

import static org.mockito.Mockito.*;

public abstract class FindUserByIdUsecaseSpy {
    public static FindUserByIdUsecase get() {
        FindUserByIdUsecase findUserByIdUsecase = spy(FindUserByIdUsecase.class);

        when(findUserByIdUsecase.handle(any()))
                .thenReturn(UserMock.get());

        return findUserByIdUsecase;
    }
}
