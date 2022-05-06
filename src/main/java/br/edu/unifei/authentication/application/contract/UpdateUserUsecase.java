package br.edu.unifei.authentication.application.contract;

import br.edu.unifei.authentication.application.dto.UpdateUserDTO;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.exception.LoginInUseException;
import br.edu.unifei.authentication.domain.exception.UserNotFoundException;

public interface UpdateUserUsecase {
    User handle(UpdateUserDTO dto) throws UserNotFoundException, LoginInUseException;
}
