package br.edu.unifei.authentication.presentation.springweb.controller;

import br.edu.unifei.authentication.application.contract.CreateUserUsecase;
import br.edu.unifei.authentication.application.contract.CreateUserUsecaseSpy;
import br.edu.unifei.authentication.application.dto.CreateUserDTO;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.entity.UserMock;
import br.edu.unifei.authentication.presentation.springweb.request.CreateUserRequest;
import br.edu.unifei.authentication.presentation.springweb.request.CreateUserRequestMock;
import br.edu.unifei.authentication.presentation.springweb.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CreateUserControllerTest {
    CreateUserUsecase createUserUsecaseSpy;
    CreateUserController sut;

    @BeforeEach
    void setup() {
        createUserUsecaseSpy = CreateUserUsecaseSpy.get();
        sut = new CreateUserController(createUserUsecaseSpy);
    }

    @Test
    void shouldCallCreateUserUsecaseWithCorrectParams() {
        ArgumentCaptor<CreateUserDTO> argumentCaptor = ArgumentCaptor.forClass(CreateUserDTO.class);
        CreateUserRequest body = CreateUserRequestMock.get();
        sut.handle(body);
        verify(createUserUsecaseSpy).handle(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().login(), body.getLogin());
        assertEquals(argumentCaptor.getValue().permissionLevel(), body.getPermissionLevel());
    }

    @Test
    void shouldReturnAUserResponseOnSuccess() {
        User user = UserMock.get();
        when(createUserUsecaseSpy.handle(any()))
                .thenReturn(user);
        UserResponse response = sut.handle(CreateUserRequestMock.get());
        assertEquals(response, new UserResponse(user));
    }
}
