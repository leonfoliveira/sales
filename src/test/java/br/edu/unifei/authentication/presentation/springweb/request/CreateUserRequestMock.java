package br.edu.unifei.authentication.presentation.springweb.request;

import br.edu.unifei.authentication.domain.entity.PermissionLevel;
import com.github.javafaker.Faker;

public abstract class CreateUserRequestMock {
    public static CreateUserRequest get() {
        Faker faker = new Faker();
        return CreateUserRequest.builder()
                .login(faker.name().firstName())
                .permissionLevel(faker.options().option(PermissionLevel.class))
                .build();
    }
}
