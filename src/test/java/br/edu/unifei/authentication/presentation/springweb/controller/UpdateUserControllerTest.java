package br.edu.unifei.authentication.presentation.springweb.controller;

import br.edu.unifei.authentication.application.contract.UpdateUserUsecase;
import br.edu.unifei.authentication.application.contract.UpdateUserUsecaseSpy;
import br.edu.unifei.authentication.application.dto.UpdateUserDTO;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.entity.UserMock;
import br.edu.unifei.authentication.presentation.springweb.request.UpdateUserRequest;
import br.edu.unifei.authentication.presentation.springweb.request.UpdateUserRequestMock;
import br.edu.unifei.authentication.presentation.springweb.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateUserControllerTest {
    UpdateUserUsecase updateUserUsecaseSpy;
    UpdateUserController sut;

    @BeforeEach
    void setup() {
        updateUserUsecaseSpy = UpdateUserUsecaseSpy.get();
        sut = new UpdateUserController(updateUserUsecaseSpy);
    }

    @Test
    void shouldCallUpdateUserUsecaseWithCorrectParams() {
        ArgumentCaptor<UpdateUserDTO> argumentCaptor = ArgumentCaptor.forClass(UpdateUserDTO.class);
        UUID userId = UUID.randomUUID();
        UpdateUserRequest body = UpdateUserRequestMock.get();
        sut.handle(userId.toString(), body);
        verify(updateUserUsecaseSpy).handle(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().id(), userId);
        assertEquals(argumentCaptor.getValue().login(), body.getLogin());
        assertEquals(argumentCaptor.getValue().permissionLevel(), body.getPermissionLevel());
    }

    @Test
    void shouldReturnAUserResponseOnSuccess() {
        User user = UserMock.get();
        when(updateUserUsecaseSpy.handle(any()))
                .thenReturn(user);
        UserResponse response = sut.handle(UUID.randomUUID().toString(), UpdateUserRequestMock.get());
        assertEquals(response, new UserResponse(user));
    }
}
