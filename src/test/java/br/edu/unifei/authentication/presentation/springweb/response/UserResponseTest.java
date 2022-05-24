package br.edu.unifei.authentication.presentation.springweb.response;

import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.entity.UserMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserResponseTest {
    @Test
    void shouldMapFromAUserEntityCorrectly() {
        User user = UserMock.get();
        UserResponse userResponse = new UserResponse(user);
        assertEquals(userResponse.id(), user.getId());
        assertEquals(userResponse.login(), user.getLogin());
        assertEquals(userResponse.permissionLevel(), user.getPermissionLevel());
        assertEquals(userResponse.isActive(), user.getIsActive());
    }
}
