package br.edu.unifei.transaction.persistence.jpa.entity;

import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public abstract class JpaSaleMock {
    public static JpaSale get() {
        JpaSale jpaSale = new JpaSale();

        jpaSale.setId(UUID.randomUUID());
        jpaSale.setJpaSaleItem(List.of(JpaSaleItemMock.get()));
        jpaSale.setCreatedAt(LocalDateTime.now());

        return jpaSale;
    }
}
