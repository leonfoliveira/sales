package br.edu.unifei.transaction.domain.exception;

import br.edu.unifei.common.exception.ConflictException;

public class SaleNotFoundException extends ConflictException {
    public SaleNotFoundException() {
        super("Sale not found.");
    }
}
