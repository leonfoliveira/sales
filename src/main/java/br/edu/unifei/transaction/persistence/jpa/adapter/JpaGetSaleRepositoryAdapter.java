package br.edu.unifei.transaction.persistence.jpa.adapter;

import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.persistence.jpa.entity.JpaProduct;
import br.edu.unifei.inventory.persistence.jpa.repository.JpaProductRepository;
import br.edu.unifei.transaction.application.db.repository.GetSaleRepository;
import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleItem;
import br.edu.unifei.transaction.persistence.jpa.entity.JpaSale;
import br.edu.unifei.transaction.persistence.jpa.entity.JpaSaleItem;
import br.edu.unifei.transaction.persistence.jpa.repository.JpaSaleItemRepository;
import br.edu.unifei.transaction.persistence.jpa.repository.JpaSaleRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class JpaGetSaleRepositoryAdapter implements GetSaleRepository {
    private final JpaSaleRepository saleRepository;
    private final JpaSaleItemRepository saleItemRepository;
    private final JpaProductRepository productRepository;

    @Override
    public List<Sale> getAll() {
        List<JpaSale> jpaSale = saleRepository.findAll();
        return jpaSale
                .stream()
                .map(sale -> findById(sale.getId()).get())
                .toList();
    }

    @Override
    public Optional<Sale> findById(UUID id) {
        List<JpaSaleItem> jpaSaleItems = saleItemRepository.findAllBySaleId(id);
        List<SaleItem> saleItems = new ArrayList<>();
        for(JpaSaleItem item : jpaSaleItems) {
            Optional<Product> product = productRepository.findById(item.getProductId())
                    .map(JpaProduct::toDomainEntity);
            saleItems.add(item.toDomainEntity(product.get()));
        }
        return Optional.of(saleRepository.findById(id).get().toDomainEntity(saleItems));
    }
}
