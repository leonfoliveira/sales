package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.contract.FindUserByIdUsecase;
import br.edu.unifei.authentication.application.contract.FindUserByIdUsecaseSpy;
import br.edu.unifei.authentication.application.db.repository.GetUserRepository;
import br.edu.unifei.authentication.application.db.repository.GetUserRepositorySpy;
import br.edu.unifei.authentication.application.db.repository.UpdateUserRepository;
import br.edu.unifei.authentication.application.db.repository.UpdateUserRepositorySpy;
import br.edu.unifei.authentication.application.dto.UpdateUserDTO;
import br.edu.unifei.authentication.application.dto.UpdateUserDTOMock;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.entity.UserMock;
import br.edu.unifei.authentication.domain.exception.LoginInUseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DbUpdateUserUsecaseTest {
    GetUserRepository getUserRepositorySpy;
    FindUserByIdUsecase findUserByIdUsecaseSpy;
    UpdateUserRepository updateUserRepository;
    DbUpdateUserUsecase sut;

    @BeforeEach
    void setup() {
        getUserRepositorySpy = GetUserRepositorySpy.get();
        when(getUserRepositorySpy.findByLogin(any()))
                .thenReturn(Optional.empty());
        findUserByIdUsecaseSpy = FindUserByIdUsecaseSpy.get();
        updateUserRepository = UpdateUserRepositorySpy.get();
        sut = new DbUpdateUserUsecase(getUserRepositorySpy, findUserByIdUsecaseSpy, updateUserRepository);
    }

    @Test
    void shouldCallGetUserRepositoryWithCorrectParam() {
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        UpdateUserDTO dto = UpdateUserDTOMock.get();
        sut.handle(dto);
        verify(getUserRepositorySpy).findByLogin(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue(), dto.login());
    }

    @Test
    void shouldThrowLoginInUseExceptionIfGetUserRepositoryReturnsSomeUserWithADifferentId() {
        when(getUserRepositorySpy.findByLogin(any()))
                .thenReturn(Optional.of(UserMock.get()));
        assertThrows(LoginInUseException.class,
                () -> sut.handle(UpdateUserDTOMock.get()));
    }

    @Test
    void shouldCallFindUserByIdUsecaseWithCorrectParams() {
        UpdateUserDTO dto = UpdateUserDTOMock.get();
        sut.handle(dto);
        verify(findUserByIdUsecaseSpy).handle(dto.id());
    }

    @Test
    void shouldCallUpdateUserRepositoryWithCorrectParams() {
        UpdateUserDTO dto = UpdateUserDTOMock.get();
        User user = UserMock.get();
        when(findUserByIdUsecaseSpy.handle(any()))
                .thenReturn(user);
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        sut.handle(dto);
        verify(updateUserRepository).update(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().getId(), user.getId());
        assertEquals(argumentCaptor.getValue().getLogin(), dto.login());
        assertEquals(argumentCaptor.getValue().getPassword(), user.getPassword());
        assertEquals(argumentCaptor.getValue().getPermissionLevel(), dto.permissionLevel());
        assertEquals(argumentCaptor.getValue().getIsActive(), user.getIsActive());
    }
}
