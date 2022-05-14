package br.edu.unifei.authentication.application.db.usecase;

import br.edu.unifei.authentication.application.contract.LoginUserUsecase;
import br.edu.unifei.authentication.application.db.infra.HashComparer;
import br.edu.unifei.authentication.application.db.infra.TokenGenerator;
import br.edu.unifei.authentication.application.db.repository.GetUserRepository;
import br.edu.unifei.authentication.application.model.Authorization;
import br.edu.unifei.authentication.application.model.AuthorizationPayload;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.domain.exception.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DbLoginUserUsecase implements LoginUserUsecase {
    private final GetUserRepository getUserRepository;
    private final HashComparer hashComparer;
    private final TokenGenerator tokenGenerator;

    @Override
    public Authorization handle(String login, String password) throws InvalidCredentialsException {
        User user = getUserRepository.findByLogin(login)
                .orElseThrow(InvalidCredentialsException::new);

        if (!user.getIsActive()
                || user.getPassword() == null
                || !hashComparer.compare(password, user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        AuthorizationPayload payload = new AuthorizationPayload(
                user.getId(),
                user.getLogin(),
                user.getPermissionLevel());
        String accessToken = tokenGenerator.generate(payload);

        return new Authorization(accessToken, payload);
    }
}
