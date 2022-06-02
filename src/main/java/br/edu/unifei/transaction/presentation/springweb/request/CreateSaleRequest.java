package br.edu.unifei.transaction.presentation.springweb.request;

import br.edu.unifei.transaction.application.dto.CreateSaleItemDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
public class CreateSaleRequest {
    @NotEmpty
    List<CreateSaleItemDTO> items;
}
