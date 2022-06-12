package br.edu.unifei.inventory.presentation.springweb.controller;


import br.edu.unifei.common.annotation.RoleManager;
import br.edu.unifei.common.validator.IsUUID;
import br.edu.unifei.inventory.application.contract.ToggleActivenessProductUsecase;
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
@RequestMapping("/products")
@Validated
@Tag(name = "Products")
@RequiredArgsConstructor
public class ToggleActivenessProductController {
    private final ToggleActivenessProductUsecase toggleActivenessProductUsecase;

    @PatchMapping("/{productId}/toggle")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Toggle activeness")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
    })
    @RoleManager
    public void handle(@PathVariable @IsUUID String productId) {
        toggleActivenessProductUsecase.handle(UUID.fromString(productId));
    }
}
