package br.edu.unifei.inventory.application.dto;

import br.edu.unifei.inventory.domain.entity.UnitType;
import com.github.javafaker.Faker;

public class CreateProductDTOMock {
    public static CreateProductDTO get() {
        Faker faker = new Faker();
        return new CreateProductDTO(
                faker.lorem().sentence(),
                faker.number().digits(10),
                faker.random().nextDouble(),
                faker.options().option(UnitType.class)
        );
    }
}
