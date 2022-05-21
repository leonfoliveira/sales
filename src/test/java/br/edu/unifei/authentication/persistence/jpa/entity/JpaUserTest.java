package br.edu.unifei.authentication.persistence.jpa.entity;

import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.entity.UserMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JpaUserTest {
    @Test
    void shouldMapCreateAJpaEntityCorrectly() {
        User user = UserMock.get();
        JpaUser jpaUser = new JpaUser(user);
        assertEquals(jpaUser.getId(), user.getId());
        assertEquals(jpaUser.getLogin(), user.getLogin());
        assertEquals(jpaUser.getPassword(), user.getPassword());
        assertEquals(jpaUser.getPermissionLevel(), user.getPermissionLevel());
        assertEquals(jpaUser.getIsActive(), user.getIsActive());
    }

    @Test
    void shouldMapToADomainEntityCorrectly() {
        User user = UserMock.get();
        JpaUser jpaUser = new JpaUser(user);
        assertEquals(jpaUser.toDomainEntity(), user);
    }
}
