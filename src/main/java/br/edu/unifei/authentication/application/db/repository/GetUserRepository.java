package br.edu.unifei.authentication.application.db.repository;

import br.edu.unifei.authentication.domain.entity.User;

import java.util.List;

public interface GetUserRepository {
    List<User> getAll();
}
