package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.contract.SetPasswordUserUsecase;
import br.edu.unifei.authentication.application.db.infra.HashGenerator;
import br.edu.unifei.authentication.application.db.repository.GetUserRepository;
import br.edu.unifei.authentication.application.db.repository.UpdateUserRepository;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.exception.PasswordAlreadySetException;
import br.edu.unifei.authentication.domain.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DbSetPasswordUserUsecase implements SetPasswordUserUsecase {
    private final GetUserRepository getUserRepository;
    private final UpdateUserRepository updateUserRepository;
    private final HashGenerator hashGenerator;

    @Override
    public void handle(UUID userId, String password) throws UserNotFoundException, PasswordAlreadySetException {
        User user = getUserRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (user.getPassword() != null) {
            throw new PasswordAlreadySetException();
        }

        user.setPassword(hashGenerator.generate(password));
        updateUserRepository.update(user);
    }
}
