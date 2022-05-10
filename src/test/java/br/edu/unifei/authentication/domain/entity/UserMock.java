package br.edu.unifei.authentication.domain.entity;

import com.github.javafaker.Faker;

import java.util.UUID;

public abstract class UserMock {
    public static User get() {
        Faker faker = new Faker();

        return User.builder()
                .id(UUID.randomUUID())
                .login(faker.name().firstName())
                .password(faker.internet().password())
                .permissionLevel(faker.options().option(PermissionLevel.class))
                .isActive(faker.random().nextBoolean())
                .build();
    }
}
