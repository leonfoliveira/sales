package br.edu.unifei.authentication.application.usecase.db.repository;

import br.edu.unifei.authentication.domain.entity.User;

import java.util.List;

public interface GetUserRepository {
    List<User> getAll();
}
