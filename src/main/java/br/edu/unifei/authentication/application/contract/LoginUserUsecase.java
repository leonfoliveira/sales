package br.edu.unifei.authentication.application.contract;

import br.edu.unifei.authentication.application.model.Authorization;
import br.edu.unifei.authentication.domain.exception.InvalidCredentialsException;

public interface LoginUserUsecase {
    Authorization handle(String login, String password) throws InvalidCredentialsException;
}
