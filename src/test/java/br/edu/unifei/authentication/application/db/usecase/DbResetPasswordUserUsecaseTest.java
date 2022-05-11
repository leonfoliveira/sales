package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.db.repository.GetUserRepository;
import br.edu.unifei.authentication.application.db.repository.GetUserRepositorySpy;
import br.edu.unifei.authentication.application.db.repository.UpdateUserRepository;
import br.edu.unifei.authentication.application.db.repository.UpdateUserRepositorySpy;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DbResetPasswordUserUsecaseTest {
    GetUserRepository getUserRepositorySpy;
    UpdateUserRepository updateUserRepositorySpy;
    DbResetPasswordUserUsecase sut;

    @BeforeEach
    void setup() {
        getUserRepositorySpy = GetUserRepositorySpy.get();
        updateUserRepositorySpy = UpdateUserRepositorySpy.get();
        sut = new DbResetPasswordUserUsecase(getUserRepositorySpy, updateUserRepositorySpy);
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
    void shouldCallUpdateUserRepositoryWithCorrectParams() {
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        sut.handle(UUID.randomUUID());
        verify(updateUserRepositorySpy).update(argumentCaptor.capture());
        assertNull(argumentCaptor.getValue().getPassword());
    }
}