package br.edu.unifei.transaction.presentation.springweb.request;

import br.edu.unifei.transaction.application.dto.CreateSaleItemDTO;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Builder
public class CreateSaleRequest {
    @NotEmpty
    private List<CreateSaleItemDTO> items;
    private String placeholder;
}
