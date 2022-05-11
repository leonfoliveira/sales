package br.edu.unifei.authentication.application.dto;

import br.edu.unifei.authentication.domain.entity.PermissionLevel;
import com.github.javafaker.Faker;

import java.util.UUID;

public abstract class UpdateUserDTOMock {
    public static UpdateUserDTO get() {
        Faker faker = new Faker();
        return new UpdateUserDTO(
                UUID.randomUUID(),
                faker.name().firstName(),
                faker.options().option(PermissionLevel.class));
    }
}
