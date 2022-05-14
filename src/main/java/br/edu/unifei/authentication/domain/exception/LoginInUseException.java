package br.edu.unifei.authentication.domain.exception;

import br.edu.unifei.common.exception.ConflictException;

public class LoginInUseException extends ConflictException {
    public LoginInUseException() {
        super("The provided login is already in use by another user.");
    }
}
