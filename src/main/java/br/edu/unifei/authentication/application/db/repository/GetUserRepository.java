package br.edu.unifei.authentication.application.db.repository;

import br.edu.unifei.authentication.domain.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GetUserRepository {
    List<User> getAll();

    Optional<User> findById(UUID id);

    Optional<User> findByLogin(String login);
}
