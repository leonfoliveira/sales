package br.edu.unifei.inventory.application.db.repository;

import static org.mockito.Mockito.spy;

public abstract class InsertProductRepositorySpy {
    public static InsertProductRepository get() {
        return spy(InsertProductRepository.class);
    }
}
