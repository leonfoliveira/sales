package br.edu.unifei.authentication.application.contract;

import br.edu.unifei.authentication.domain.exception.PasswordAlreadySetException;
import br.edu.unifei.authentication.domain.exception.UserNotFoundException;

import java.util.UUID;

public interface SetPasswordUserUsecase {
    void handle(UUID userId, String password) throws UserNotFoundException, PasswordAlreadySetException;
}
