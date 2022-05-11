package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.contract.FindUserByIdUsecase;
import br.edu.unifei.authentication.application.contract.FindUserByIdUsecaseSpy;
import br.edu.unifei.authentication.application.db.repository.UpdateUserRepository;
import br.edu.unifei.authentication.application.db.repository.UpdateUserRepositorySpy;
import br.edu.unifei.authentication.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;

class DbResetPasswordUserUsecaseTest {
    FindUserByIdUsecase findUserByIdUsecaseSpy;
    UpdateUserRepository updateUserRepositorySpy;
    DbResetPasswordUserUsecase sut;

    @BeforeEach
    void setup() {
        findUserByIdUsecaseSpy = FindUserByIdUsecaseSpy.get();
        updateUserRepositorySpy = UpdateUserRepositorySpy.get();
        sut = new DbResetPasswordUserUsecase(findUserByIdUsecaseSpy, updateUserRepositorySpy);
    }

    @Test
    void shouldCallFindUserByIdUsecaseSpyWithCorrectParam() {
        ArgumentCaptor<UUID> argumentCaptor = ArgumentCaptor.forClass(UUID.class);
        UUID userId = UUID.randomUUID();
        sut.handle(userId);
        verify(findUserByIdUsecaseSpy).handle(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue(), userId);
    }

    @Test
    void shouldCallUpdateUserRepositoryWithCorrectParams() {
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        sut.handle(UUID.randomUUID());
        verify(updateUserRepositorySpy).update(argumentCaptor.capture());
        assertNull(argumentCaptor.getValue().getPassword());
    }
}