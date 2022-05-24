package br.edu.unifei.authentication.application.model;

import br.edu.unifei.authentication.domain.entity.PermissionLevel;
import com.github.javafaker.Faker;

import java.util.UUID;

public abstract class AuthorizationMock {
    public static Authorization get() {
        Faker faker = new Faker();

        AuthorizationPayload payload = new AuthorizationPayload(
                UUID.randomUUID(),
                faker.name().firstName(),
                faker.options().option(PermissionLevel.class));
        return new Authorization(faker.lorem().characters(), payload);
    }
}
