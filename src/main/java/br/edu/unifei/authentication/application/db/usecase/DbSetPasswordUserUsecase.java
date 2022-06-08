package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.contract.FindUserByIdUsecase;
import br.edu.unifei.authentication.application.contract.SetPasswordUserUsecase;
import br.edu.unifei.authentication.application.db.infrastructure.HashGenerator;
import br.edu.unifei.authentication.application.db.repository.UpdateUserRepository;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.exception.PasswordAlreadySetException;
import br.edu.unifei.authentication.domain.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DbSetPasswordUserUsecase implements SetPasswordUserUsecase {
    private final FindUserByIdUsecase findUserByIdUsecase;
    private final UpdateUserRepository updateUserRepository;
    private final HashGenerator hashGenerator;

    @Override
    public void handle(UUID userId, String password) throws UserNotFoundException, PasswordAlreadySetException {
        User user = findUserByIdUsecase.handle(userId);

        if (user.getPassword() != null) {
            throw new PasswordAlreadySetException();
        }

        user.setPassword(hashGenerator.generate(password));
        updateUserRepository.update(user);
    }
}
