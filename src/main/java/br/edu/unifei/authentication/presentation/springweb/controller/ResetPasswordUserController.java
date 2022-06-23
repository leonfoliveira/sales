package br.edu.unifei.authentication.presentation.springweb.controller;

import br.edu.unifei.authentication.application.contract.ResetPasswordUserUsecase;
import br.edu.unifei.authentication.presentation.springweb.response.ResetPasswordUserResponse;
import br.edu.unifei.common.annotation.RoleAdmin;
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
public class ResetPasswordUserController {
    private final ResetPasswordUserUsecase resetPasswordUserUsecase;

    @PatchMapping("/{userId}/password/reset")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Reset a User's password")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @RoleAdmin
    public ResetPasswordUserResponse handle(@PathVariable @IsUUID String userId) {
        UUID uuidUserId = UUID.fromString(userId);
        resetPasswordUserUsecase.handle(uuidUserId);
        return new ResetPasswordUserResponse(uuidUserId);
    }
}
