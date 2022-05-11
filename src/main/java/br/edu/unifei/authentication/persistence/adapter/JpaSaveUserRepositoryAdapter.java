package br.edu.unifei.authentication.persistence.adapter;

import br.edu.unifei.authentication.application.db.repository.InsertUserRepository;
import br.edu.unifei.authentication.application.db.repository.UpdateUserRepository;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.exception.UserNotFoundException;
import br.edu.unifei.authentication.persistence.entity.JpaUser;
import br.edu.unifei.authentication.persistence.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class JpaSaveUserRepositoryAdapter implements InsertUserRepository, UpdateUserRepository {
    private final JpaUserRepository repository;

    @Override
    public void insert(User user) {
        JpaUser jpaUser = new JpaUser(user);
        jpaUser.setCreatedAt(LocalDateTime.now());
        repository.save(jpaUser);
    }

    @Override
    public void update(User user) {
        JpaUser existingUser = repository.findById(user.getId())
                .orElseThrow(UserNotFoundException::new);
        JpaUser jpaUser = new JpaUser(user);
        jpaUser.setCreatedAt(existingUser.getCreatedAt());
        jpaUser.setUpdatedAt(LocalDateTime.now());
        repository.save(jpaUser);
    }
}
