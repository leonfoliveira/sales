package br.edu.unifei.authentication.persistence.repository;

import br.edu.unifei.authentication.persistence.entity.JpaUserMock;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public abstract class JpaUserRepositoryMock {
    public static JpaUserRepository get() {
        JpaUserRepository jpaUserRepository = spy(JpaUserRepository.class);

        when(jpaUserRepository.findAll())
                .thenReturn(List.of(JpaUserMock.get()));
        when(jpaUserRepository.findById(any()))
                .thenReturn(Optional.of(JpaUserMock.get()));
        when(jpaUserRepository.findByLogin(any()))
                .thenReturn(Optional.of(JpaUserMock.get()));

        return jpaUserRepository;
    }
}
