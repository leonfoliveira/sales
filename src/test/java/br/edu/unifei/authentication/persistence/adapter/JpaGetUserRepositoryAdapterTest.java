package br.edu.unifei.authentication.persistence.adapter;

import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.entity.UserMock;
import br.edu.unifei.authentication.persistence.entity.JpaUser;
import br.edu.unifei.authentication.persistence.repository.JpaUserRepository;
import br.edu.unifei.authentication.persistence.repository.JpaUserRepositoryMock;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class JpaGetUserRepositoryAdapterTest {
    JpaUserRepository jpaUserRepositorySpy;
    JpaGetUserRepositoryAdapter sut;
    Faker faker = new Faker();

    @BeforeEach
    void setup() {
        jpaUserRepositorySpy = JpaUserRepositoryMock.get();
        sut = new JpaGetUserRepositoryAdapter(jpaUserRepositorySpy);
    }

    @Test
    void getAllShouldCallJpaUserRepository() {
        sut.getAll();
        verify(jpaUserRepositorySpy).findAll();
    }

    @Test
    void getAllShouldReturnJpaUserRepositoryMappedResponse() {
        User user = UserMock.get();
        when(jpaUserRepositorySpy.findAll())
                .thenReturn(List.of(new JpaUser(user)));
        List<User> result = sut.getAll();
        assertEquals(result, List.of(user));
    }

    @Test
    void findByIdShouldCallJpaUserRepositoryWithCorrectParams() {
        UUID userId = UUID.randomUUID();
        sut.findById(userId);
        verify(jpaUserRepositorySpy).findById(userId);
    }

    @Test
    void findByIdShouldReturnJpaUserRepositoryMappedResponse() {
        User user = UserMock.get();
        when(jpaUserRepositorySpy.findById(any()))
                .thenReturn(Optional.of(new JpaUser(user)));
        Optional<User> result = sut.findById(UUID.randomUUID());
        assertEquals(result, Optional.of(user));
    }

    @Test
    void findByLoginShouldCallJpaUserRepositoryWithCorrectParams() {
        String login = faker.name().firstName();
        sut.findByLogin(login);
        verify(jpaUserRepositorySpy).findByLogin(login);
    }

    @Test
    void findByLoginShouldReturnJpaUserRepositoryMappedResponse() {
        User user = UserMock.get();
        when(jpaUserRepositorySpy.findByLogin(any()))
                .thenReturn(Optional.of(new JpaUser(user)));
        Optional<User> result = sut.findByLogin(faker.name().firstName());
        assertEquals(result, Optional.of(user));
    }
}
