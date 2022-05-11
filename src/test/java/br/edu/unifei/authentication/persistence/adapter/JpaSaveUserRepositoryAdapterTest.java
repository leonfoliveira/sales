package br.edu.unifei.authentication.persistence.adapter;

import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.entity.UserMock;
import br.edu.unifei.authentication.persistence.entity.JpaUser;
import br.edu.unifei.authentication.persistence.repository.JpaUserRepository;
import br.edu.unifei.authentication.persistence.repository.JpaUserRepositoryMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class JpaSaveUserRepositoryAdapterTest {
    JpaUserRepository jpaUserRepositorySpy;
    JpaSaveUserRepositoryAdapter sut;

    @BeforeEach
    void setup() {
        jpaUserRepositorySpy = JpaUserRepositoryMock.get();
        sut = new JpaSaveUserRepositoryAdapter(jpaUserRepositorySpy);
    }

    @Test
    void insertShouldCallUserRepositoryWithCorrectParams() {
        ArgumentCaptor<JpaUser> argumentCaptor = ArgumentCaptor.forClass(JpaUser.class);
        User user = UserMock.get();
        sut.insert(user);
        verify(jpaUserRepositorySpy).save(argumentCaptor.capture());
        JpaUser jpaUser = argumentCaptor.getValue();
        assertEquals(jpaUser.toDomainEntity(), user);
        assertNotNull(jpaUser.getCreatedAt());
    }

    @Test
    void updateShouldCallUserRepositoryWithCorrectParams() {
        User oldUser = UserMock.get();
        JpaUser oldJpaUser = new JpaUser(oldUser);
        oldJpaUser.setCreatedAt(LocalDateTime.now());
        when(jpaUserRepositorySpy.findById(any()))
                .thenReturn(Optional.of(oldJpaUser));
        ArgumentCaptor<JpaUser> argumentCaptor = ArgumentCaptor.forClass(JpaUser.class);
        User user = UserMock.get();
        sut.update(user);
        verify(jpaUserRepositorySpy).save(argumentCaptor.capture());
        JpaUser jpaUser = argumentCaptor.getValue();
        assertEquals(jpaUser.toDomainEntity(), user);
        assertNotNull(jpaUser.getCreatedAt());
        assertNotNull(jpaUser.getUpdatedAt());
    }
}