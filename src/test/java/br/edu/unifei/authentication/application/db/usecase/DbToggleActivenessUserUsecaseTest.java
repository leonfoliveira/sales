package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.contract.FindUserByIdUsecase;
import br.edu.unifei.authentication.application.contract.FindUserByIdUsecaseSpy;
import br.edu.unifei.authentication.application.db.repository.UpdateUserRepository;
import br.edu.unifei.authentication.application.db.repository.UpdateUserRepositorySpy;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.entity.UserMock;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DbToggleActivenessUserUsecaseTest {
    FindUserByIdUsecase findUserByIdUsecaseSpy;
    UpdateUserRepository updateUserRepositorySpy;
    DbToggleActivenessUserUsecase sut;
    Faker faker = new Faker();

    @BeforeEach
    void setup() {
        findUserByIdUsecaseSpy = FindUserByIdUsecaseSpy.get();
        updateUserRepositorySpy = UpdateUserRepositorySpy.get();
        sut = new DbToggleActivenessUserUsecase(findUserByIdUsecaseSpy, updateUserRepositorySpy);
    }

    @Test
    void shouldCallGetUserRepositoryWithCorrectParam() {
        UUID userId = UUID.randomUUID();
        sut.handle(userId);
        verify(findUserByIdUsecaseSpy).handle(userId);
    }

    @Test
    void shouldCallUpdateUserRepositoryCorrectParams() {
        User user = UserMock.get();
        Boolean isActive = faker.random().nextBoolean();
        user.setIsActive(isActive);
        when(findUserByIdUsecaseSpy.handle(any()))
                .thenReturn(user);
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        sut.handle(UUID.randomUUID());
        verify(updateUserRepositorySpy).update(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().getIsActive(), !isActive);
    }
}
