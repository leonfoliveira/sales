package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.contract.FindUserByIdUsecase;
import br.edu.unifei.authentication.application.contract.FindUserByIdUsecaseSpy;
import br.edu.unifei.authentication.application.db.infrastructure.HashGenerator;
import br.edu.unifei.authentication.application.db.infrastructure.HashGeneratorSpy;
import br.edu.unifei.authentication.application.db.repository.UpdateUserRepository;
import br.edu.unifei.authentication.application.db.repository.UpdateUserRepositorySpy;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.entity.UserMock;
import br.edu.unifei.authentication.domain.exception.PasswordAlreadySetException;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DbSetPasswordUserUsecaseTest {
    FindUserByIdUsecase findUserByIdUsecaseSpy;
    UpdateUserRepository updateUserRepositorySpy;
    HashGenerator hashGeneratorSpy;
    DbSetPasswordUserUsecase sut;
    Faker faker = new Faker();

    @BeforeEach
    void setup() {
        User user = UserMock.get();
        user.setPassword(null);
        findUserByIdUsecaseSpy = FindUserByIdUsecaseSpy.get();
        when(findUserByIdUsecaseSpy.handle(any()))
                .thenReturn(user);
        updateUserRepositorySpy = UpdateUserRepositorySpy.get();
        hashGeneratorSpy = HashGeneratorSpy.get();
        sut = new DbSetPasswordUserUsecase(findUserByIdUsecaseSpy, updateUserRepositorySpy, hashGeneratorSpy);
    }

    @Test
    void shouldCallFindUserByIdUsecaseSpyWithCorrectParam() {
        UUID userId = UUID.randomUUID();
        sut.handle(userId, faker.internet().password());
        verify(findUserByIdUsecaseSpy).handle(userId);
    }

    @Test
    void shouldThrowIfFoundUserHasANonNullablePassword() {
        User user = UserMock.get();
        when(findUserByIdUsecaseSpy.handle(any()))
                .thenReturn(user);
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
