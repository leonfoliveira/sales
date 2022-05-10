package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.contract.GetAllUserUsecase;
import br.edu.unifei.authentication.application.db.repository.GetUserRepository;
import br.edu.unifei.authentication.domain.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DbGetAllUserUsecase implements GetAllUserUsecase {
    private final GetUserRepository getUserRepository;

    @Override
    public List<User> handle() {
        return getUserRepository.getAll();
    }
}
