package br.edu.unifei.authentication.application.contract;

import br.edu.unifei.authentication.domain.entity.User;

import java.util.List;

public interface GetAllUserUsecase {
    List<User> handle();
}
