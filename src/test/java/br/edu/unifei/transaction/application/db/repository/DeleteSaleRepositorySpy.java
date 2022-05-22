package br.edu.unifei.transaction.application.db.repository;

import static org.mockito.Mockito.spy;

public abstract class DeleteSaleRepositorySpy {
    public static DeleteSaleRepository get() {
        return spy(DeleteSaleRepository.class);
    }
}
