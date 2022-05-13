package br.edu.unifei.authentication.application.db.repository;

import br.edu.unifei.authentication.domain.entity.User;

public interface InsertUserRepository {
    void insert(User user);
}
