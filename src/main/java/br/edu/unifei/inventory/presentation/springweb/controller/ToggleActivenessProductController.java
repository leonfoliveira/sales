package br.edu.unifei.inventory.presentation.springweb.controller;


import br.edu.unifei.inventory.application.contract.ToggleActivenessProductUsecase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/products/toggle-activeness/")
@Validated
@Tag(name = "ToggleActiveness")
@RequiredArgsConstructor
public class ToggleActivenessProductController {
    private final ToggleActivenessProductUsecase toggleActivenessProductUsecase;

    @PostMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Toggle activeness")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
    })
    public void handle(@RequestParam @Valid String productId){
        toggleActivenessProductUsecase.handle(UUID.fromString(productId));
    }

}
