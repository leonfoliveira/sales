package br.edu.unifei.transaction.application.db.usecase;

import br.edu.unifei.inventory.application.db.repository.GetProductRepository;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.exception.ProductNotFoundException;
import br.edu.unifei.transaction.application.contract.CreateSaleUsecase;
import br.edu.unifei.transaction.application.db.repository.InsertSaleRepository;
import br.edu.unifei.transaction.application.dto.CreateSaleDTO;
import br.edu.unifei.transaction.application.dto.CreateSaleItemDTO;
import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleItem;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DbCreateSaleUseCase implements CreateSaleUsecase {
    private final GetProductRepository getProductRepository;
    private final InsertSaleRepository insertSaleRepository;

    @Override
    public Sale handle(CreateSaleDTO dto) throws ProductNotFoundException {

        for(CreateSaleItemDTO createSaleItemDTO : dto.items()) {
            Optional<Product> product = getProductRepository.findById(createSaleItemDTO.productId());
            if(!product.isPresent() || !product.get().getIsActive()) {
                throw new ProductNotFoundException();
            }
        }

        List<SaleItem> items = dto.items().stream()
                .map(item -> SaleItem.builder()
                        .product(getProductRepository.findById(item.productId()).get())
                        .amount(item.amount())
                        .unitPrice(new BigDecimal(item.unitPrice().toString()))
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
