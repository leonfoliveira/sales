package br.edu.unifei.transaction.application.contract;

import java.util.UUID;

import static org.mockito.Mockito.*;

public abstract class DeleteSaleUseCaseSpy {
    public static DeleteSaleUsecase get() {
        DeleteSaleUsecase deleteSaleUsecase = spy(DeleteSaleUsecase.class);
        doNothing().when(deleteSaleUsecase).handle(UUID.randomUUID());
        return deleteSaleUsecase;
    }
}
