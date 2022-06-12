package br.edu.unifei.inventory.presentation.springweb.request;

import br.edu.unifei.common.validator.IsEnum;
import br.edu.unifei.inventory.domain.entity.UnitType;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class UpdateProductRequest {
    @NotNull
    @Length(max = 200)
    private String title;
    @NotEmpty
    @Digits(integer = 15, fraction = 0)
    private String barCode;
    @NotNull
    private Double unitPrice;
    @IsEnum(enumClass = UnitType.class)
    private String unitType;
}