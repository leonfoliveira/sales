package br.edu.unifei.inventory.presentation.springweb.controller;


import br.edu.unifei.inventory.application.contract.CreateProductUsecase;
import br.edu.unifei.inventory.application.dto.CreateProductDTO;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.presentation.springweb.request.CreateProductRequest;
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

@RestController
@RequestMapping("/products")
@Validated
@Tag(name="Products")
@RequiredArgsConstructor
public class CreateProductController {
    private final CreateProductUsecase createProductUsecase;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new Product")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "409", description = "Barcode already in use")
    })
    public ProductResponse handle(@RequestBody @Valid CreateProductRequest body){
        CreateProductDTO dto = new CreateProductDTO(body.getTitle(), body.getBarCode(), body.getUnitPrice(), body.getUnitType());
        Product product = createProductUsecase.handle(dto);
        return new ProductResponse(product);
    }

}
