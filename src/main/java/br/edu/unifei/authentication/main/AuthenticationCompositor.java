package br.edu.unifei.authentication.main;

import br.edu.unifei.authentication.application.contract.*;
import br.edu.unifei.authentication.application.db.usecase.*;
import br.edu.unifei.authentication.infrastructure.bcrypt.BCryptAdapter;
import br.edu.unifei.authentication.infrastructure.jwt.JwtAdapter;
import br.edu.unifei.authentication.persistence.jpa.adapter.JpaGetUserRepositoryAdapter;
import br.edu.unifei.authentication.persistence.jpa.adapter.JpaSaveUserRepositoryAdapter;
import br.edu.unifei.authentication.persistence.jpa.repository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationCompositor {
    private final BCryptAdapter bCryptAdapter = new BCryptAdapter();
    private final JwtAdapter jwtAdapter;
    private final JpaGetUserRepositoryAdapter jpaGetUserRepositoryAdapter;
    private final JpaSaveUserRepositoryAdapter jpaSaveUserRepositoryAdapter;

    public AuthenticationCompositor(JpaUserRepository jpaUserRepository,
                                    @Value("${jwt.secret}") String jwtSecret,
                                    @Value("${jwt.expiration}") Integer jwtExpiration) {
        jwtAdapter = new JwtAdapter(jwtSecret, jwtExpiration);
        jpaGetUserRepositoryAdapter = new JpaGetUserRepositoryAdapter(jpaUserRepository);
        jpaSaveUserRepositoryAdapter = new JpaSaveUserRepositoryAdapter(jpaUserRepository);
    }

    @Bean
    public CreateUserUsecase createUserUsecase() {
        return new DbCreateUserUsecase(jpaGetUserRepositoryAdapter,
                jpaSaveUserRepositoryAdapter);
    }

    @Bean
    public FindUserByIdUsecase findUserByIdUsecase() {
        return new DbFindUserByIdUsecase(jpaGetUserRepositoryAdapter);
    }

    @Bean
    public GetAllUserUsecase getAllUserUsecase() {
        return new DbGetAllUserUsecase(jpaGetUserRepositoryAdapter);
    }

    @Bean
    public LoginUserUsecase loginUserUsecase() {
        return new DbLoginUserUsecase(jpaGetUserRepositoryAdapter,
                bCryptAdapter,
                jwtAdapter);
    }

    @Bean
    public ResetPasswordUserUsecase resetPasswordUserUsecase() {
        return new DbResetPasswordUserUsecase(findUserByIdUsecase(),
                jpaSaveUserRepositoryAdapter);
    }

    @Bean
    public SetPasswordUserUsecase setPasswordUserUsecase() {
        return new DbSetPasswordUserUsecase(findUserByIdUsecase(),
                jpaSaveUserRepositoryAdapter,
                bCryptAdapter);
    }

    @Bean
    public ToggleActivenessUserUsecase toggleActivenessUserUsecase() {
        return new DbToggleActivenessUserUsecase(findUserByIdUsecase(),
                jpaSaveUserRepositoryAdapter);
    }

    @Bean
    public UpdateUserUsecase updateUserUsecase() {
        return new DbUpdateUserUsecase(jpaGetUserRepositoryAdapter,
                findUserByIdUsecase(),
                jpaSaveUserRepositoryAdapter);
    }
}
