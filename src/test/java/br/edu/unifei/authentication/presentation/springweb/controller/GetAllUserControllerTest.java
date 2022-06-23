package br.edu.unifei.authentication.presentation.springweb.controller;

import br.edu.unifei.authentication.application.contract.GetAllUserUsecase;
import br.edu.unifei.authentication.application.contract.GetAllUserUsecaseSpy;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.entity.UserMock;
import br.edu.unifei.authentication.presentation.springweb.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetAllUserControllerTest {
    GetAllUserUsecase getAllUserUsecaseSpy;
    GetAllUserController sut;

    @BeforeEach
    void setup() {
        getAllUserUsecaseSpy = GetAllUserUsecaseSpy.get();
        sut = new GetAllUserController(getAllUserUsecaseSpy);
    }

    @Test
    void shouldCallGetAllUserUsecase() {
        sut.handle(null);
        verify(getAllUserUsecaseSpy).handle();
    }

    @Test
    void shouldReturnAListOfUserResponseOnSuccess() {
        User user = UserMock.get();
        when(getAllUserUsecaseSpy.handle())
                .thenReturn(List.of(user));
        List<UserResponse> response = sut.handle(null);
        assertEquals(response, List.of(new UserResponse(user)));
    }
}
