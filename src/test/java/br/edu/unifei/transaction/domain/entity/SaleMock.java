package br.edu.unifei.transaction.domain.entity;

import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class SaleMock {
    public static Sale get() {
        Faker faker = new Faker();

        List<SaleItem> items = new ArrayList<>();
        int numberOfItems = faker.random().nextInt(1,10);
        for(int i = 0; i < numberOfItems; i++) {
            items.add(SaleItemMock.get());
        }

        return Sale.builder()
                .id(UUID.randomUUID())
                .items(items)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
