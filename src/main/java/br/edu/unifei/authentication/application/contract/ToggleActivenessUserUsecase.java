package br.edu.unifei.authentication.application.contract;

import br.edu.unifei.authentication.domain.exception.UserNotFoundException;

import java.util.UUID;

public interface ToggleActivenessUserUsecase {
    void handle(UUID userId) throws UserNotFoundException;
}
