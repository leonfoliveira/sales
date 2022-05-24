package br.edu.unifei.authentication.application.contract;


import br.edu.unifei.authentication.domain.entity.UserMock;

import java.util.List;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public abstract class GetAllUserUsecaseSpy {
    public static GetAllUserUsecase get() {
        GetAllUserUsecase getAllUserUsecase = spy(GetAllUserUsecase.class);

        when(getAllUserUsecase.handle())
                .thenReturn(List.of(UserMock.get()));

        return getAllUserUsecase;
    }
}
