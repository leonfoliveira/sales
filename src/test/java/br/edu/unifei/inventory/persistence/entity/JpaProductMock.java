package br.edu.unifei.inventory.persistence.entity;

import br.edu.unifei.inventory.domain.entity.UnitType;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class JpaProductMock {
    public static JpaProduct get() {
        JpaProduct jpaProduct = new JpaProduct();
        Faker faker = new Faker();

        jpaProduct.setId(UUID.randomUUID());
        jpaProduct.setTitle(faker.lorem().sentence());
        jpaProduct.setBarCode(faker.number().digits(10));
        jpaProduct.setUnitPrice(faker.random().nextDouble());
        jpaProduct.setUnitType(faker.options().option(UnitType.class));
        jpaProduct.setIsActive(faker.random().nextBoolean());
        jpaProduct.setCreatedAt(LocalDateTime.now());
        jpaProduct.setUpdatedAt(LocalDateTime.now());

        return jpaProduct;
    }
}
