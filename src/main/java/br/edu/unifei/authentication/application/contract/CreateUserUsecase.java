package br.edu.unifei.authentication.application.contract;

import br.edu.unifei.authentication.application.dto.CreateUserDTO;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.common.exception.ConflictException;

public interface CreateUserUsecase {
    User handle(CreateUserDTO dto) throws ConflictException;
}
