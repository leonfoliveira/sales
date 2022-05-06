package br.edu.unifei.inventory.application.contract;

import br.edu.unifei.inventory.application.dto.UpdateProductDTO;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.exception.BarCodeInUseException;
import br.edu.unifei.inventory.domain.exception.ProductNotFoundException;

public interface UpdateProductUsecase {
    Product handle(UpdateProductDTO dto) throws ProductNotFoundException, BarCodeInUseException;
}
