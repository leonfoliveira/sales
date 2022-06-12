package br.edu.unifei.transaction.presentation.springweb.controller;

import br.edu.unifei.common.annotation.RoleCollaborator;
import br.edu.unifei.transaction.application.contract.CreateSaleUsecase;
import br.edu.unifei.transaction.application.dto.CreateSaleDTO;
import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.presentation.springweb.request.CreateSaleRequest;
import br.edu.unifei.transaction.presentation.springweb.response.SaleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sales")
@Validated
@Tag(name = "Sales")
@RequiredArgsConstructor
public class CreateSaleController {
    private final CreateSaleUsecase createSaleUsecase;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new sale")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @RoleCollaborator
    public SaleResponse handle(@RequestBody @Valid CreateSaleRequest body) {
        CreateSaleDTO dto = new CreateSaleDTO(body.getItems());
        Sale sale = createSaleUsecase.handle(dto);
        return new SaleResponse(sale);
    }
}
