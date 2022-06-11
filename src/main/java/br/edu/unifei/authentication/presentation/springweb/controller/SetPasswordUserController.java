package br.edu.unifei.authentication.presentation.springweb.controller;

import br.edu.unifei.authentication.application.contract.SetPasswordUserUsecase;
import br.edu.unifei.authentication.presentation.springweb.request.SetPasswordUserRequest;
import br.edu.unifei.common.validator.IsUUID;
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
@RequestMapping("/users")
@Validated
@Tag(name = "Users")
@RequiredArgsConstructor
public class SetPasswordUserController {
    private final SetPasswordUserUsecase setPasswordUserUsecase;

    @PatchMapping("/{userId}/password/set")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Set a User's password")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Success"),
            @ApiResponse(responseCode = "403", description = "Password already set"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public void handle(@RequestParam @IsUUID String userId, @RequestBody @Valid SetPasswordUserRequest body) {
        setPasswordUserUsecase.handle(UUID.fromString(userId), body.getPassword());
    }
}
