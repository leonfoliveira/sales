package br.edu.unifei.inventory.presentation.springweb.request;

import br.edu.unifei.inventory.domain.entity.UnitType;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Getter
@Builder
public class CreateProductRequest {
    @NotNull
    @Length(max = 200)
    private String title;
    @NotEmpty
    @Digits(integer = 15, fraction = 0)
    private String barCode;
    @NotNull
    private Double unitPrice;
    @NotNull
    @Min(0)
    @Max(1)
    private UnitType unitType;
}
