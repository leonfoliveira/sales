package br.edu.unifei.transaction.persistence.jpa.entity;

import br.edu.unifei.inventory.persistence.jpa.entity.JpaProductMock;
import com.github.javafaker.Faker;

import java.math.BigDecimal;

public abstract class JpaSaleItemMock {
    public static JpaSaleItem get() {
        JpaSaleItem jpaSaleItem = new JpaSaleItem();
        Faker faker = new Faker();

        jpaSaleItem.setId(faker.random().nextLong());
        jpaSaleItem.setProduct(JpaProductMock.get());
        jpaSaleItem.setAmount(faker.random().nextInt(1,10));
        jpaSaleItem.setUnitPrice(new BigDecimal(faker.random().nextDouble()));

        return jpaSaleItem;
    }
}
