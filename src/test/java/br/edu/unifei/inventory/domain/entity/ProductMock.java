package br.edu.unifei.inventory.domain.entity;

import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class ProductMock {
    public static Product get() {
        Faker faker = new Faker();
        return Product.builder()
                .id(UUID.randomUUID())
                .title(faker.lorem().sentence())
                .barCode(faker.number().digits(10))
                .unitPrice(BigDecimal.valueOf(faker.random().nextDouble()))
                .unitType(faker.options().option(UnitType.class))
                .isActive(faker.random().nextBoolean())
                .build();
    }
}
