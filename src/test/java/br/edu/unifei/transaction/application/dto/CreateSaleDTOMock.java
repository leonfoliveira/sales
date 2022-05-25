package br.edu.unifei.transaction.application.dto;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class CreateSaleDTOMock {
    public static CreateSaleDTO get() {
        Faker faker = new Faker();

        List<CreateSaleItemDTO> items = new ArrayList<>();
        int numberOfItems = faker.random().nextInt(1,10);
        for(int i = 0; i < numberOfItems; i++) {
            items.add(CreateSaleItemDTOMock.get());
        }

        return new CreateSaleDTO(items);
    }
}
