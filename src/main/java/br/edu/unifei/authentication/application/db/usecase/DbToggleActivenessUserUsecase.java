package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.contract.ToggleActivenessUserUsecase;
import br.edu.unifei.authentication.application.db.repository.GetUserRepository;
import br.edu.unifei.authentication.application.db.repository.UpdateUserRepository;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DbToggleActivenessUserUsecase implements ToggleActivenessUserUsecase {
    private final GetUserRepository getUserRepository;
    private final UpdateUserRepository updateUserRepository;

    @Override
    public void handle(UUID userId) throws UserNotFoundException {
        User user = getUserRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        user.setIsActive(!user.getIsActive());
        updateUserRepository.update(user);
    }
}
