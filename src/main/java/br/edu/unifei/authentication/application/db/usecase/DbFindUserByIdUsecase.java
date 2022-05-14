package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.contract.FindUserByIdUsecase;
import br.edu.unifei.authentication.application.db.repository.GetUserRepository;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DbFindUserByIdUsecase implements FindUserByIdUsecase {
    private final GetUserRepository getUserRepository;

    @Override
    public User handle(UUID userId) throws UserNotFoundException {
        return getUserRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }
}
