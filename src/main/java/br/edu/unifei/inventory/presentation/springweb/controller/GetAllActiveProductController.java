package br.edu.unifei.inventory.presentation.springweb.controller;

import br.edu.unifei.inventory.application.contract.GetAllProductUsecase;
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

@RestController
@RequestMapping("/products")
@Validated
@Tag(name = "active products")
@RequiredArgsConstructor
public class GetAllActiveProductController {
    private final GetAllProductUsecase getAllProductUsecase;

    @GetMapping("/active")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all active products")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
    })
    public void handle() {
        getAllProductUsecase.handle(false);
    }
}
