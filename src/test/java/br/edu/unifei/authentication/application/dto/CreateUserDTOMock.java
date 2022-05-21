package br.edu.unifei.authentication.application.dto;

import br.edu.unifei.authentication.domain.entity.PermissionLevel;
import com.github.javafaker.Faker;

public abstract class CreateUserDTOMock {
    public static CreateUserDTO get() {
        Faker faker = new Faker();
        return new CreateUserDTO(
                faker.name().firstName(),
                faker.options().option(PermissionLevel.class));
    }
}
