package br.edu.unifei.transaction.application.db.repository;

import static org.mockito.Mockito.spy;

public abstract class InsertSaleRepositorySpy {
    public static InsertSaleRepository get() {
        return spy(InsertSaleRepository.class);
    }
}
