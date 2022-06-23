package br.edu.unifei.transaction.application.db.usecase;

import br.edu.unifei.common.exception.BusinessRuleException;
import br.edu.unifei.inventory.application.contract.FindProductByIdUsecase;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.entity.UnitType;
import br.edu.unifei.inventory.domain.exception.ProductNotFoundException;
import br.edu.unifei.transaction.application.contract.CreateSaleUsecase;
import br.edu.unifei.transaction.application.db.repository.InsertSaleRepository;
import br.edu.unifei.transaction.application.dto.CreateSaleDTO;
import br.edu.unifei.transaction.application.dto.CreateSaleItemDTO;
import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleItem;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DbCreateSaleUseCase implements CreateSaleUsecase {
    private final FindProductByIdUsecase findProductByIdUsecase;
    private final InsertSaleRepository insertSaleRepository;

    @Override
    public Sale handle(CreateSaleDTO dto) throws ProductNotFoundException {

        HashMap<UUID, Product> products = new HashMap<>();
        for (CreateSaleItemDTO createSaleItemDTO : dto.items()) {
            Product product = findProductByIdUsecase.handle(createSaleItemDTO.productId());
            if (!product.getIsActive()) {
                throw new ProductNotFoundException();
            }
            double amount = createSaleItemDTO.amount();
            if (product.getUnitType() == UnitType.UNIT && amount != (int) amount) {
                throw new BusinessRuleException("Amount need to be an integer value");
            }
            products.put(createSaleItemDTO.productId(), product);
        }

        List<SaleItem> items = dto.items().stream()
                .map(item -> SaleItem.builder()
                        .product(products.get(item.productId()))
                        .amount(item.amount())
                        .unitPrice(products.get(item.productId()).getUnitPrice())
                        .build())
                .collect(Collectors.toList());

        Sale sale = Sale.builder()
                .id(UUID.randomUUID())
                .items(items)
                .createdAt(LocalDateTime.now()).build();

        insertSaleRepository.insert(sale);

        return sale;
    }
}
