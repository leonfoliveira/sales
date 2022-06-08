package br.edu.unifei.transaction.persistence.jpa.repository;

import br.edu.unifei.transaction.persistence.jpa.entity.JpaSaleMock;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public abstract class JpaSaleRepositorySpy {
    public static JpaSaleRepository get() {
        JpaSaleRepository jpaSaleRepository = spy(JpaSaleRepository.class);

        when(jpaSaleRepository.findAll())
                .thenReturn(List.of(JpaSaleMock.get()));
        when(jpaSaleRepository.findById(any()))
                .thenReturn(Optional.of(JpaSaleMock.get()));

        return jpaSaleRepository;
    }
}
