package br.edu.unifei.transaction.persistence.jpa.repository;

import static org.mockito.Mockito.spy;

public abstract class JpaSaleRepositorySpy {
    public static JpaSaleRepository get() {
        JpaSaleRepository jpaSaleRepository = spy(JpaSaleRepository.class);
        return jpaSaleRepository;
    }
}
