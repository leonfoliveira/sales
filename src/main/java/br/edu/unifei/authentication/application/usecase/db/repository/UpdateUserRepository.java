package br.edu.unifei.authentication.application.usecase.db.repository;

import br.edu.unifei.authentication.domain.entity.User;

public interface UpdateUserRepository {
    void update(User user);
}
