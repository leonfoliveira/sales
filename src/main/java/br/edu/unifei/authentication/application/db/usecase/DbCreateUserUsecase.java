package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.contract.CreateUserUsecase;
import br.edu.unifei.authentication.application.db.repository.GetUserRepository;
import br.edu.unifei.authentication.application.db.repository.InsertUserRepository;
import br.edu.unifei.authentication.application.dto.CreateUserDTO;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.exception.LoginInUseException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class DbCreateUserUsecase implements CreateUserUsecase {
    private final GetUserRepository getUserRepository;
    private final InsertUserRepository insertUserRepository;

    @Override
    public User handle(CreateUserDTO dto) throws LoginInUseException {
        Optional<User> existingUser = getUserRepository.findByLogin(dto.login());
        if (existingUser.isPresent()) {
            throw new LoginInUseException();
        }

        User user = User.builder()
                .id(UUID.randomUUID())
                .login(dto.login())
                .permissionLevel(dto.permissionLevel())
                .isActive(true)
                .build();
        insertUserRepository.insert(user);
        return user;
    }
}
