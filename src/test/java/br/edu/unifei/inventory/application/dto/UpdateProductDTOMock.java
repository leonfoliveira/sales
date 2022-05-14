package br.edu.unifei.inventory.application.dto;

import br.edu.unifei.inventory.domain.entity.UnitType;
import com.github.javafaker.Faker;

import java.util.UUID;

public abstract class UpdateProductDTOMock {
    public static UpdateProductDTO get() {
        Faker faker = new Faker();
        return new UpdateProductDTO(
                UUID.randomUUID(),
                faker.lorem().sentence(),
                faker.number().digits(10),
                faker.random().nextDouble(),
                faker.options().option(UnitType.class)
        );
    }
}
