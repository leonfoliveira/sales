package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.db.repository.GetUserRepository;
import br.edu.unifei.authentication.application.db.repository.GetUserRepositorySpy;
import br.edu.unifei.authentication.application.db.repository.UpdateUserRepository;
import br.edu.unifei.authentication.application.db.repository.UpdateUserRepositorySpy;
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

class DbToggleActivenessUserUsecaseTest {
    GetUserRepository getUserRepositorySpy;
    UpdateUserRepository updateUserRepositorySpy;
    DbToggleActivenessUserUsecase sut;
    Faker faker = new Faker();

    @BeforeEach
    void setup() {
        getUserRepositorySpy = GetUserRepositorySpy.get();
        updateUserRepositorySpy = UpdateUserRepositorySpy.get();
        sut = new DbToggleActivenessUserUsecase(getUserRepositorySpy, updateUserRepositorySpy);
    }

    @Test
    void shouldCallGetUserRepositoryWithCorrectParam() {
        UUID userId = UUID.randomUUID();
        sut.handle(userId);
        verify(getUserRepositorySpy).findById(userId);
    }

    @Test
    void shouldThrowUserNotFoundExceptionIfGetUserRepositoryReturnsEmptyOptional() {
        when(getUserRepositorySpy.findById(any()))
                .thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,
                () -> sut.handle(UUID.randomUUID()));
    }

    @Test
    void shouldCallUpdateUserRepositoryCorrectParams() {
        User user = UserMock.get();
        Boolean isActive = faker.random().nextBoolean();
        user.setIsActive(isActive);
        when(getUserRepositorySpy.findById(any()))
                .thenReturn(Optional.of(user));
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        sut.handle(UUID.randomUUID());
        verify(updateUserRepositorySpy).update(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().getIsActive(), !isActive);
    }
}