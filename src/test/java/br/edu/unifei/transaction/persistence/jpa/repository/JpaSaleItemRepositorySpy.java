package br.edu.unifei.transaction.persistence.jpa.repository;

import static org.mockito.Mockito.spy;

public abstract class JpaSaleItemRepositorySpy {
    public static JpaSaleItemRepository get() {
        JpaSaleItemRepository jpaSaleItemRepository = spy(JpaSaleItemRepository.class);
        return jpaSaleItemRepository;
    }
}
