package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.contract.FindUserByLoginUsecase;
import br.edu.unifei.authentication.application.db.repository.GetUserRepository;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DbFindUserByLoginUsecase implements FindUserByLoginUsecase {
    private final GetUserRepository getUserRepository;

    @Override
    public User handle(String login) throws UserNotFoundException {
        return getUserRepository.findByLogin(login)
                .orElseThrow(UserNotFoundException::new);
    }
}
