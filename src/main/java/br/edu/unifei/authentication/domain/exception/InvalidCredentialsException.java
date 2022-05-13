package br.edu.unifei.authentication.domain.exception;

import br.edu.unifei.common.exception.UnauthorizedException;

public class InvalidCredentialsException extends UnauthorizedException {
    public InvalidCredentialsException() {
        super("Wrong password or user does not exists.");
    }
}
