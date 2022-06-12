package br.edu.unifei.authentication.presentation.springweb.controller;

import br.edu.unifei.authentication.application.contract.UpdateUserUsecase;
import br.edu.unifei.authentication.application.dto.UpdateUserDTO;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.presentation.springweb.request.UpdateUserRequest;
import br.edu.unifei.authentication.presentation.springweb.response.UserResponse;
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

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Validated
@Tag(name = "Users")
@RequiredArgsConstructor
public class UpdateUserController {
    private final UpdateUserUsecase updateUserUsecase;

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a User")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "409", description = "Login already in use")
    })
    @RoleAdmin
    public UserResponse handle(
            @RequestParam @IsUUID String userId,
            @RequestBody @Valid UpdateUserRequest body) {
        UpdateUserDTO dto = new UpdateUserDTO(
                UUID.fromString(userId),
                body.getLogin(),
                body.getPermissionLevel());
        User user = updateUserUsecase.handle(dto);
        return new UserResponse(user);
    }
}
