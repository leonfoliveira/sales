package br.edu.unifei.transaction.application.dto;

import com.github.javafaker.Faker;

import java.util.UUID;

public class CreateSaleItemDTOMock {
    public static CreateSaleItemDTO get() {
        Faker faker = new Faker();
        return new CreateSaleItemDTO(
                UUID.randomUUID(),
                Double.valueOf(faker.random().nextInt(1, 15))
        );
    }
}
