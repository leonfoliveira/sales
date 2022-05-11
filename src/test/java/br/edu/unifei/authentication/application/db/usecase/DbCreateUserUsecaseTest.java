package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.db.repository.GetUserRepository;
import br.edu.unifei.authentication.application.db.repository.GetUserRepositorySpy;
import br.edu.unifei.authentication.application.db.repository.InsertUserRepository;
import br.edu.unifei.authentication.application.db.repository.InsertUserRepositorySpy;
import br.edu.unifei.authentication.application.dto.CreateUserDTO;
import br.edu.unifei.authentication.application.dto.CreateUserDTOMock;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.entity.UserMock;
import br.edu.unifei.authentication.domain.exception.LoginInUseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DbCreateUserUsecaseTest {
    GetUserRepository getUserRepositorySpy;
    InsertUserRepository insertUserRepositorySpy;
    DbCreateUserUsecase sut;

    @BeforeEach
    void setup() {
        getUserRepositorySpy = GetUserRepositorySpy.get();
        when(getUserRepositorySpy.findByLogin(any()))
                .thenReturn(Optional.empty());
        insertUserRepositorySpy = InsertUserRepositorySpy.get();
        sut = new DbCreateUserUsecase(getUserRepositorySpy, insertUserRepositorySpy);
    }

    @Test
    void shouldCallGetUserRepositoryWithCorrectParam() {
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        CreateUserDTO dto = CreateUserDTOMock.get();
        sut.handle(dto);
        verify(getUserRepositorySpy).findByLogin(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue(), dto.login());
    }

    @Test
    void shouldThrowLoginInUseExceptionIfGetUserRepositoryReturnsSomeUser() {
        when(getUserRepositorySpy.findByLogin(any()))
                .thenReturn(Optional.of(UserMock.get()));
        assertThrows(LoginInUseException.class,
                () -> sut.handle(CreateUserDTOMock.get()));
    }

    @Test
    void shouldCallInsertUserRepositoryWithCorrectParams() {
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        CreateUserDTO dto = CreateUserDTOMock.get();
        sut.handle(dto);
        verify(insertUserRepositorySpy).insert(argumentCaptor.capture());
        assertNotNull(argumentCaptor.getValue().getId());
        assertEquals(argumentCaptor.getValue().getLogin(), dto.login());
        assertNull(argumentCaptor.getValue().getPassword());
        assertEquals(argumentCaptor.getValue().getPermissionLevel(), dto.permissionLevel());
        assertEquals(argumentCaptor.getValue().getIsActive(), true);
    }

    @Test
    void shouldReturnAUserInstanceOnSuccess() {
        CreateUserDTO dto = CreateUserDTOMock.get();
        User result = sut.handle(dto);
        assertNotNull(result.getId());
        assertEquals(result.getLogin(), dto.login());
        assertNull(result.getPassword());
        assertEquals(result.getPermissionLevel(), dto.permissionLevel());
        assertEquals(result.getIsActive(), true);
    }
}