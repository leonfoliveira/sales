package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.db.infra.HashComparer;
import br.edu.unifei.authentication.application.db.infra.HashComparerSpy;
import br.edu.unifei.authentication.application.db.infra.TokenGenerator;
import br.edu.unifei.authentication.application.db.infra.TokenGeneratorSpy;
import br.edu.unifei.authentication.application.db.repository.GetUserRepository;
import br.edu.unifei.authentication.application.db.repository.GetUserRepositorySpy;
import br.edu.unifei.authentication.application.model.Authorization;
import br.edu.unifei.authentication.application.model.AuthorizationPayload;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.entity.UserMock;
import br.edu.unifei.authentication.domain.exception.InvalidCredentialsException;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DbLoginUserUsecaseTest {
    GetUserRepository getUserRepositorySpy;
    HashComparer hashComparerSpy;
    TokenGenerator tokenGeneratorSpy;
    DbLoginUserUsecase sut;
    Faker faker = new Faker();

    @BeforeEach
    void setup() {
        getUserRepositorySpy = GetUserRepositorySpy.get();
        User user = UserMock.get();
        user.setIsActive(true);
        when(getUserRepositorySpy.findByLogin(any()))
                .thenReturn(Optional.of(user));
        hashComparerSpy = HashComparerSpy.get();
        tokenGeneratorSpy = TokenGeneratorSpy.get();
        sut = new DbLoginUserUsecase(getUserRepositorySpy, hashComparerSpy, tokenGeneratorSpy);
    }

    @Test
    void shouldCallGetUserRepositoryWithCorrectParam() {
        String login = faker.name().firstName();
        sut.handle(login, faker.internet().password());
        verify(getUserRepositorySpy).findByLogin(login);
    }

    @Test
    void shouldThrowInvalidCredentialsExceptionIfGetUserRepositoryReturnsEmptyOptional() {
        when(getUserRepositorySpy.findByLogin(any()))
                .thenReturn(Optional.empty());
        assertThrows(InvalidCredentialsException.class,
                () -> sut.handle(faker.name().firstName(), faker.internet().password()));
    }

    @Test
    void shouldThrowInvalidCredentialsExceptionIfGetUserRepositoryReturnsInactiveUser() {
        User user = UserMock.get();
        user.setIsActive(false);
        when(getUserRepositorySpy.findByLogin(any()))
                .thenReturn(Optional.of(user));
        assertThrows(InvalidCredentialsException.class,
                () -> sut.handle(faker.name().firstName(), faker.internet().password()));
    }

    @Test
    void shouldThrowInvalidCredentialsExceptionIfGetUserRepositoryReturnsUserWithNullPassword() {
        User user = UserMock.get();
        user.setPassword(null);
        user.setIsActive(true);
        when(getUserRepositorySpy.findByLogin(any()))
                .thenReturn(Optional.of(user));
        assertThrows(InvalidCredentialsException.class,
                () -> sut.handle(faker.name().firstName(), faker.internet().password()));
    }

    @Test
    void shouldCallHashComparerWithCorrectParams() {
        User user = UserMock.get();
        user.setIsActive(true);
        when(getUserRepositorySpy.findByLogin(any()))
                .thenReturn(Optional.of(user));
        String password = faker.internet().password();
        sut.handle(faker.name().firstName(), password);
        verify(hashComparerSpy).compare(password, user.getPassword());
    }

    @Test
    void shouldThrowInvalidCredentialsExceptionIfHashComparerReturnsFalse() {
        when(hashComparerSpy.compare(any(), any()))
                .thenReturn(false);
        assertThrows(InvalidCredentialsException.class,
                () -> sut.handle(faker.name().firstName(), faker.internet().password()));
    }

    @Test
    void shouldCallTokenGeneratorWithCorrectParams() {
        User user = UserMock.get();
        user.setIsActive(true);
        when(getUserRepositorySpy.findByLogin(any()))
                .thenReturn(Optional.of(user));
        ArgumentCaptor<AuthorizationPayload> argumentCaptor = ArgumentCaptor.forClass(AuthorizationPayload.class);
        sut.handle(faker.name().firstName(), faker.internet().password());
        verify(tokenGeneratorSpy).generate(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().userId(), user.getId());
        assertEquals(argumentCaptor.getValue().login(), user.getLogin());
        assertEquals(argumentCaptor.getValue().permissionLevel(), user.getPermissionLevel());
    }

    @Test
    void shouldReturnAnAuthorizationModelOnSuccess() {
        User user = UserMock.get();
        user.setIsActive(true);
        when(getUserRepositorySpy.findByLogin(any()))
                .thenReturn(Optional.of(user));
        String accessToken = faker.lorem().characters();
        when(tokenGeneratorSpy.generate(any()))
                .thenReturn(accessToken);
        Authorization result = sut.handle(faker.name().firstName(), faker.internet().password());
        assertEquals(result.accessToken(), accessToken);
        assertEquals(result.payload().userId(), user.getId());
        assertEquals(result.payload().login(), user.getLogin());
        assertEquals(result.payload().permissionLevel(), user.getPermissionLevel());
    }
}