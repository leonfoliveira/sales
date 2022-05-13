package br.edu.unifei.authentication.domain.exception;

import br.edu.unifei.common.exception.ConflictException;

public class LoginInUseException extends ConflictException {
    public LoginInUseException(String message) {
        super(message);
    }
}
