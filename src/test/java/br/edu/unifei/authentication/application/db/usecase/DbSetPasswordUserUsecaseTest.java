package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.db.infra.HashGenerator;
import br.edu.unifei.authentication.application.db.infra.HashGeneratorSpy;
import br.edu.unifei.authentication.application.db.repository.GetUserRepository;
import br.edu.unifei.authentication.application.db.repository.GetUserRepositorySpy;
import br.edu.unifei.authentication.application.db.repository.UpdateUserRepository;
import br.edu.unifei.authentication.application.db.repository.UpdateUserRepositorySpy;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.entity.UserMock;
import br.edu.unifei.authentication.domain.exception.PasswordAlreadySetException;
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

class DbSetPasswordUserUsecaseTest {
    GetUserRepository getUserRepositorySpy;
    UpdateUserRepository updateUserRepositorySpy;
    HashGenerator hashGeneratorSpy;
    DbSetPasswordUserUsecase sut;
    Faker faker = new Faker();

    @BeforeEach
    void setup() {
        User user = UserMock.get();
        user.setPassword(null);
        getUserRepositorySpy = GetUserRepositorySpy.get();
        when(getUserRepositorySpy.findById(any()))
                .thenReturn(Optional.of(user));
        updateUserRepositorySpy = UpdateUserRepositorySpy.get();
        hashGeneratorSpy = HashGeneratorSpy.get();
        sut = new DbSetPasswordUserUsecase(getUserRepositorySpy, updateUserRepositorySpy, hashGeneratorSpy);
    }

    @Test
    void shouldCallGetUserRepositoryWithCorrectParam() {
        UUID userId = UUID.randomUUID();
        sut.handle(userId, faker.internet().password());
        verify(getUserRepositorySpy).findById(userId);
    }

    @Test
    void shouldThrowUserNotFoundExceptionIfGetUserRepositoryReturnsEmptyOptional() {
        when(getUserRepositorySpy.findById(any()))
                .thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,
                () -> sut.handle(UUID.randomUUID(), faker.internet().password()));
    }

    @Test
    void shouldThrowIfFoundUserHasANonNullablePassword() {
        User user = UserMock.get();
        when(getUserRepositorySpy.findById(any()))
                .thenReturn(Optional.of(user));
        assertThrows(PasswordAlreadySetException.class,
                () -> sut.handle(UUID.randomUUID(), faker.internet().password()));
    }

    @Test
    void shouldCallHashGeneratorWithCorrectParams() {
        String password = faker.internet().password();
        sut.handle(UUID.randomUUID(), password);
        verify(hashGeneratorSpy).generate(password);
    }

    @Test
    void shouldCallUpdateUserRepositoryWithCorrectParams() {
        String hash = faker.lorem().characters();
        when(hashGeneratorSpy.generate(any()))
                .thenReturn(hash);
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        String password = faker.internet().password();
        sut.handle(UUID.randomUUID(), password);
        verify(updateUserRepositorySpy).update(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().getPassword(), hash);
    }
}