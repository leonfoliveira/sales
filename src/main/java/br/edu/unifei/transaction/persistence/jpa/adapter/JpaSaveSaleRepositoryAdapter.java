package br.edu.unifei.transaction.persistence.jpa.adapter;

import br.edu.unifei.transaction.application.db.repository.DeleteSaleRepository;
import br.edu.unifei.transaction.application.db.repository.InsertSaleRepository;
import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.entity.SaleItem;
import br.edu.unifei.transaction.domain.exception.SaleNotFoundException;
import br.edu.unifei.transaction.persistence.jpa.entity.JpaSale;
import br.edu.unifei.transaction.persistence.jpa.entity.JpaSaleItem;
import br.edu.unifei.transaction.persistence.jpa.repository.JpaSaleItemRepository;
import br.edu.unifei.transaction.persistence.jpa.repository.JpaSaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@RequiredArgsConstructor
public class JpaSaveSaleRepositoryAdapter implements InsertSaleRepository, DeleteSaleRepository {
    JpaSaleRepository saleRepository;
    JpaSaleItemRepository saleItemRepository;

    @Override
    public void insert(Sale sale) {
        JpaSale jpaSale = new JpaSale(sale);
        saleRepository.save(jpaSale);
        for(SaleItem saleItem : sale.getItems()) {
            JpaSaleItem jpaSaleItem = new JpaSaleItem(saleItem, sale.getId());
            saleItemRepository.save(jpaSaleItem);
        }
    }

    @Override
    public void delete(UUID id) {
        JpaSale existingSale = saleRepository.findById(id)
                .orElseThrow(SaleNotFoundException::new);
        JpaSale jpaSale = new JpaSale(existingSale.toDomainEntity(Collections.emptyList()));
        jpaSale.setDeletedAt(LocalDateTime.now());
        saleRepository.save(jpaSale);
    }
}
