package br.edu.unifei.authentication.domain.exception;

import br.edu.unifei.common.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("User not found.");
    }
}
