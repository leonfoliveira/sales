package br.edu.unifei.authentication.persistence.jpa.adapter;

import br.edu.unifei.authentication.application.db.repository.GetUserRepository;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.persistence.jpa.entity.JpaUser;
import br.edu.unifei.authentication.persistence.jpa.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class JpaGetUserRepositoryAdapter implements GetUserRepository {
    private final JpaUserRepository repository;

    @Override
    public List<User> getAll() {
        return repository.findAll()
                .stream()
                .map(JpaUser::toDomainEntity)
                .toList();
    }

    @Override
    public Optional<User> findById(UUID userId) {
        return repository.findById(userId)
                .map(JpaUser::toDomainEntity);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return repository.findByLogin(login)
                .map(JpaUser::toDomainEntity);
    }
}
