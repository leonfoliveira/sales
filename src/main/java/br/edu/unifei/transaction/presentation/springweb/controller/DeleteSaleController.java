package br.edu.unifei.transaction.presentation.springweb.controller;

import br.edu.unifei.common.validator.IsUUID;
import br.edu.unifei.transaction.application.contract.DeleteSaleUsecase;
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
@Tag(name="Sales")
@RequiredArgsConstructor
public class DeleteSaleController {
    private final DeleteSaleUsecase deleteSaleUsecase;

    @DeleteMapping("/{SaleId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete sale")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Sale not found")
    })
    public void handle(@RequestParam @IsUUID String saleId) {
        deleteSaleUsecase.handle(UUID.fromString(saleId));
    }
}
