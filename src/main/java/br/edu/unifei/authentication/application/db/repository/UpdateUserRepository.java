package br.edu.unifei.authentication.application.db.repository;

import br.edu.unifei.authentication.domain.entity.User;

public interface UpdateUserRepository {
    void update(User user);
}
