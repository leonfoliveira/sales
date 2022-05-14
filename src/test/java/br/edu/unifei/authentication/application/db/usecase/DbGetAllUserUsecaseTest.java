package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.db.repository.GetUserRepository;
import br.edu.unifei.authentication.application.db.repository.GetUserRepositorySpy;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.entity.UserMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DbGetAllUserUsecaseTest {
    GetUserRepository getUserRepositorySpy;
    DbGetAllUserUsecase sut;

    @BeforeEach
    void setup() {
        getUserRepositorySpy = GetUserRepositorySpy.get();
        sut = new DbGetAllUserUsecase(getUserRepositorySpy);
    }

    @Test
    void shouldCallDbGetAllUserUsecase() {
        sut.handle();
        verify(getUserRepositorySpy)
                .getAll();
    }

    @Test
    void shouldReturnAnEmptyListIfGetUserRepositorySpyReturnsNoUser() {
        when(getUserRepositorySpy.getAll())
                .thenReturn(List.of());
        List<User> users = sut.handle();
        assertEquals(users.size(), 0);
    }

    @Test
    void shouldReturnAListOfUsersOnSuccess() {
        List<User> users = List.of(UserMock.get());
        when(getUserRepositorySpy.getAll())
                .thenReturn(users);
        List<User> result = sut.handle();
        assertEquals(result, users);
    }
}
