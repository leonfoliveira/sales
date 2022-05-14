package br.edu.unifei.inventory.application.db.repository;

import static org.mockito.Mockito.spy;

public abstract class UpdateProductRepositorySpy {
    public static UpdateProductRepository get() {
        return spy(UpdateProductRepository.class);
    }
}
