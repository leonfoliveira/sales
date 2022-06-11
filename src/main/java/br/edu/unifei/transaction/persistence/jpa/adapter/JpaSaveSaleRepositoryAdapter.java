package br.edu.unifei.transaction.persistence.jpa.adapter;

import br.edu.unifei.transaction.application.db.repository.DeleteSaleRepository;
import br.edu.unifei.transaction.application.db.repository.InsertSaleRepository;
import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.domain.exception.SaleNotFoundException;
import br.edu.unifei.transaction.persistence.jpa.entity.JpaSale;
import br.edu.unifei.transaction.persistence.jpa.repository.JpaSaleRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
public class JpaSaveSaleRepositoryAdapter implements InsertSaleRepository, DeleteSaleRepository {
    private final JpaSaleRepository saleRepository;

    @Override
    public void insert(Sale sale) {
        JpaSale jpaSale = new JpaSale(sale);
        saleRepository.save(jpaSale);
    }

    @Override
    public void delete(UUID id) {
        JpaSale existingSale = saleRepository.findById(id)
                .orElseThrow(SaleNotFoundException::new);
        JpaSale jpaSale = new JpaSale(existingSale.toDomainEntity());
        jpaSale.setDeletedAt(LocalDateTime.now());
        saleRepository.save(jpaSale);
    }
}
