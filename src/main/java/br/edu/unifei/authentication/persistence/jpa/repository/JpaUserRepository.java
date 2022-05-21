package br.edu.unifei.authentication.persistence.jpa.repository;

import br.edu.unifei.authentication.persistence.jpa.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<JpaUser, UUID> {
    Optional<JpaUser> findByLogin(String login);
}
