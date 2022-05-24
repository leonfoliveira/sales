package br.edu.unifei.authentication.presentation.springweb.controller;

import br.edu.unifei.authentication.application.contract.ToggleActivenessUserUsecase;
import br.edu.unifei.common.validator.IsUUID;
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
@RequestMapping("/users")
@Validated
@Tag(name = "Users")
@RequiredArgsConstructor
public class ToggleActivenessUserController {
    private final ToggleActivenessUserUsecase toggleActivenessUserUsecase;

    @PatchMapping("/{userId}/toggle")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Toggle a User's activeness")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Success"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public void handle(@RequestParam @IsUUID String userId) {
        toggleActivenessUserUsecase.handle(UUID.fromString(userId));
    }
}
