package br.edu.unifei.authentication.application.contract;

import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.exception.UserNotFoundException;

public interface FindUserByLoginUsecase {
    User handle(String login) throws UserNotFoundException;
}
