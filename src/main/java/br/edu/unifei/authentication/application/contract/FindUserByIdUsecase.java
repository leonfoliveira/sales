package br.edu.unifei.authentication.application.contract;

import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.exception.UserNotFoundException;

import java.util.UUID;

public interface FindUserByIdUsecase {
    User handle(UUID userId) throws UserNotFoundException;
}
