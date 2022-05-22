package br.edu.unifei.transaction.domain.entity;

import br.edu.unifei.inventory.domain.entity.ProductMock;
import com.github.javafaker.Faker;

import java.math.BigDecimal;

public abstract class SaleItemMock {
    public static SaleItem get() {
        Faker faker = new Faker();
        return SaleItem.builder()
                .product(ProductMock.get())
                .amount(Double.valueOf(faker.random().nextInt(1,15)))
                .unitPrice(BigDecimal.valueOf(faker.random().nextDouble()))
                .build();
    }
}
