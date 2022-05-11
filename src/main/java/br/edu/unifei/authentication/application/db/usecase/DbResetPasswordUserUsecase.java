package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.contract.FindUserByIdUsecase;
import br.edu.unifei.authentication.application.contract.ResetPasswordUserUsecase;
import br.edu.unifei.authentication.application.db.repository.UpdateUserRepository;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DbResetPasswordUserUsecase implements ResetPasswordUserUsecase {
    private final FindUserByIdUsecase findUserByIdUsecase;
    private final UpdateUserRepository updateUserRepository;

    @Override
    public void handle(UUID userId) throws UserNotFoundException {
        User user = findUserByIdUsecase.handle(userId);
        user.setPassword(null);
        updateUserRepository.update(user);
    }
}
