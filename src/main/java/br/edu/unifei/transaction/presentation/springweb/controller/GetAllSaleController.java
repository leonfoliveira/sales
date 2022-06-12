package br.edu.unifei.transaction.presentation.springweb.controller;

import br.edu.unifei.common.annotation.RoleManager;
import br.edu.unifei.transaction.application.contract.GetAllSaleUsecase;
import br.edu.unifei.transaction.presentation.springweb.response.SaleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sales")
@Validated
@Tag(name = "Sales")
@RequiredArgsConstructor
public class GetAllSaleController {
    private final GetAllSaleUsecase getAllSaleUsecase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all sales")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
    })
    @RoleManager
    public List<SaleResponse> handle() {
        return getAllSaleUsecase.handle().stream()
                .map(SaleResponse::new)
                .toList();
    }
}
