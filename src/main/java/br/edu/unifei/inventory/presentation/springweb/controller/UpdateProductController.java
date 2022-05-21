package br.edu.unifei.inventory.presentation.springweb.controller;

import br.edu.unifei.common.validator.IsUUID;
import br.edu.unifei.inventory.application.contract.UpdateProductUsecase;
import br.edu.unifei.inventory.application.dto.UpdateProductDTO;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.presentation.springweb.request.UpdateProductRequest;
import br.edu.unifei.inventory.presentation.springweb.response.ProductResponse;
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
@RequestMapping("/products")
@Validated
@Tag(name = "products")
@RequiredArgsConstructor
public class UpdateProductController {
    private final UpdateProductUsecase updateProductUsecase;

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a User")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
    })
    public ProductResponse handle(
            @RequestParam @IsUUID String productId,
            @RequestBody @Valid UpdateProductRequest body){
        UpdateProductDTO dto = new UpdateProductDTO(
                UUID.fromString(productId),
                body.getTitle(),
                body.getBarCode(),
                body.getUnitPrice(),
                body.getUnitType());

        Product product = updateProductUsecase.handle(dto);
        return new ProductResponse(product);
    }

}
