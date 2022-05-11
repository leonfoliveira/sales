package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.contract.FindUserByIdUsecase;
import br.edu.unifei.authentication.application.contract.UpdateUserUsecase;
import br.edu.unifei.authentication.application.db.repository.GetUserRepository;
import br.edu.unifei.authentication.application.db.repository.UpdateUserRepository;
import br.edu.unifei.authentication.application.dto.UpdateUserDTO;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.exception.LoginInUseException;
import br.edu.unifei.authentication.domain.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class DbUpdateUserUsecase implements UpdateUserUsecase {
    private final GetUserRepository getUserRepository;
    private final FindUserByIdUsecase findUserByIdUsecase;
    private final UpdateUserRepository updateUserRepository;

    @Override
    public User handle(UpdateUserDTO dto) throws UserNotFoundException, LoginInUseException {
        User user = findUserByIdUsecase.handle(dto.id());

        Optional<User> existingUser = getUserRepository.findByLogin(dto.login());
        if (existingUser.isPresent() && !existingUser.get().getId().equals(dto.id())) {
            throw new LoginInUseException();
        }

        user.setLogin(dto.login());
        user.setPermissionLevel(dto.permissionLevel());
        updateUserRepository.update(user);
        return user;
    }
}
