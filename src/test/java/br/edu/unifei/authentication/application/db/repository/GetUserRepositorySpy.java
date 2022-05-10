package br.edu.unifei.authentication.application.db.repository;


import br.edu.unifei.authentication.domain.entity.UserMock;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public abstract class GetUserRepositorySpy {
    public static GetUserRepository get() {
        GetUserRepository getUserRepository = spy(GetUserRepository.class);

        when(getUserRepository.getAll())
                .thenReturn(List.of(UserMock.get()));
        when(getUserRepository.findById(any()))
                .thenReturn(Optional.of(UserMock.get()));
        when(getUserRepository.findByLogin(any()))
                .thenReturn(Optional.of(UserMock.get()));

        return getUserRepository;
    }
}
