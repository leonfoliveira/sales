package br.edu.unifei.authentication.persistence.repository;

import br.edu.unifei.authentication.persistence.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<JpaUser, UUID> {
    Optional<JpaUser> findByLogin(String login);
}
