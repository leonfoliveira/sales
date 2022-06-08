package br.edu.unifei.transaction.presentation.springweb.controller;

import br.edu.unifei.common.validator.IsUUID;
import br.edu.unifei.transaction.application.contract.FindSaleByIdUsecase;
import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.presentation.springweb.response.SaleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/sales")
@Validated
@Tag(name = "Sales")
@RequiredArgsConstructor
public class FindSaleByIdController {
    private final FindSaleByIdUsecase findSaleByIdUsecase;

    @GetMapping("/{saleId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get sale")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Sale not found")
    })
    public SaleResponse handle(@RequestParam @IsUUID String saleId) {
        Sale sale = findSaleByIdUsecase.handle(UUID.fromString(saleId));
        return new SaleResponse(sale);
    }

}
