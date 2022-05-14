package br.edu.unifei.authentication.presentation.springweb.request;

import br.edu.unifei.authentication.domain.entity.PermissionLevel;
import com.github.javafaker.Faker;

public class UpdateUserRequestMock {
    public static UpdateUserRequest get() {
        Faker faker = new Faker();
        return UpdateUserRequest.builder()
                .login(faker.name().firstName())
                .permissionLevel(faker.options().option(PermissionLevel.class))
                .build();
    }
}
