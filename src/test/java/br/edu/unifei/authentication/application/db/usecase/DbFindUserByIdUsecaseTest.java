package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.db.repository.GetUserRepository;
import br.edu.unifei.authentication.application.db.repository.GetUserRepositorySpy;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.entity.UserMock;
import br.edu.unifei.authentication.domain.exception.UserNotFoundException;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DbFindUserByIdUsecaseTest {
    GetUserRepository getUserRepositorySpy;
    DbFindUserByIdUsecase sut;
    Faker faker = new Faker();

    @BeforeEach
    void setup() {
        getUserRepositorySpy = GetUserRepositorySpy.get();
        sut = new DbFindUserByIdUsecase(getUserRepositorySpy);
    }

    @Test
    void shouldCallGetUserRepositoryWithCorrectParam() {
        ArgumentCaptor<UUID> argumentCaptor = ArgumentCaptor.forClass(UUID.class);
        UUID userId = UUID.randomUUID();
        sut.handle(userId);
        verify(getUserRepositorySpy).findById(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue(), userId);
    }

    @Test
    void shouldThrowUserNotFoundExceptionIfGetUserRepositoryReturnsEmptyOptional() {
        when(getUserRepositorySpy.findById(any()))
                .thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,
                () -> sut.handle(UUID.randomUUID()));
    }

    @Test
    void shouldReturnAUserEntityOnSuccess() {
        User user = UserMock.get();
        when(getUserRepositorySpy.findById(any()))
                .thenReturn(Optional.of(user));
        User result = sut.handle(UUID.randomUUID());
        assertEquals(result, user);
    }
}