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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DbFindUserByLoginUsecaseTest {
    GetUserRepository getUserRepositorySpy;
    DbFindUserByLoginUsecase sut;
    Faker faker = new Faker();

    @BeforeEach
    void setup() {
        getUserRepositorySpy = GetUserRepositorySpy.get();
        sut = new DbFindUserByLoginUsecase(getUserRepositorySpy);
    }

    @Test
    void shouldCallGetUserRepositoryWithCorrectParam() {
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        String login = faker.name().firstName();
        sut.handle(login);
        verify(getUserRepositorySpy).findByLogin(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue(), login);
    }

    @Test
    void shouldThrowUserNotFoundExceptionIfGetUserRepositoryReturnsEmptyOptional() {
        when(getUserRepositorySpy.findByLogin(any()))
                .thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,
                () -> sut.handle(faker.name().firstName()));
    }

    @Test
    void shouldReturnAUserEntityOnSuccess() {
        User user = UserMock.get();
        when(getUserRepositorySpy.findByLogin(any()))
                .thenReturn(Optional.of(user));
        User result = sut.handle(faker.name().firstName());
        assertEquals(result, user);
    }
}