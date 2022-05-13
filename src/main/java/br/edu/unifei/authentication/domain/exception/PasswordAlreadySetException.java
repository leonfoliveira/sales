package br.edu.unifei.authentication.domain.exception;

import br.edu.unifei.common.exception.ForbiddenException;

public class PasswordAlreadySetException extends ForbiddenException {
    public PasswordAlreadySetException(String message) {
        super("User password has already been set.");
    }
}
