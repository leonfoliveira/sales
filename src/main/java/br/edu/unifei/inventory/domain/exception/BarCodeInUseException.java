package br.edu.unifei.inventory.domain.exception;

import br.edu.unifei.common.exception.ConflictException;

public class BarCodeInUseException extends ConflictException {
    public BarCodeInUseException() {
        super("Barcode already in use.");
    }
}
