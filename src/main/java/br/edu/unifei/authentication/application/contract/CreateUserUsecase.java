package br.edu.unifei.authentication.application.contract;

import br.edu.unifei.authentication.application.dto.CreateUserDTO;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.exception.LoginInUseException;

public interface CreateUserUsecase {
    User handle(CreateUserDTO dto) throws LoginInUseException;
}
