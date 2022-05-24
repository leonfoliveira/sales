package br.edu.unifei.authentication.persistence.jpa.entity;

import br.edu.unifei.authentication.domain.entity.PermissionLevel;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class JpaUserMock {
    public static JpaUser get() {
        JpaUser jpaUser = new JpaUser();
        Faker faker = new Faker();

        jpaUser.setId(UUID.randomUUID());
        jpaUser.setLogin(faker.name().firstName());
        jpaUser.setPassword(faker.internet().password());
        jpaUser.setPermissionLevel(faker.options().option(PermissionLevel.class));
        jpaUser.setIsActive(faker.random().nextBoolean());
        jpaUser.setCreatedAt(LocalDateTime.now());
        jpaUser.setUpdatedAt(LocalDateTime.now());

        return jpaUser;
    }
}
