package br.edu.unifei.authentication.presentation.springweb.controller;

import br.edu.unifei.authentication.application.contract.CreateUserUsecase;
import br.edu.unifei.authentication.application.dto.CreateUserDTO;
import br.edu.unifei.authentication.domain.entity.PermissionLevel;
import br.edu.unifei.authentication.domain.entity.User;
import br.edu.unifei.authentication.presentation.springweb.request.CreateUserRequest;
import br.edu.unifei.authentication.presentation.springweb.response.UserResponse;
import br.edu.unifei.common.annotation.RoleAdmin;
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
@RequestMapping("/users")
@Validated
@Tag(name = "Users")
@RequiredArgsConstructor
public class CreateUserController {
    private final CreateUserUsecase createUserUsecase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new User")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "409", description = "Login already in use")
    })
    @RoleAdmin
    public UserResponse handle(@RequestBody @Valid CreateUserRequest body) {
        CreateUserDTO dto = new CreateUserDTO(body.getLogin(),
                PermissionLevel.valueOf(body.getPermissionLevel()));
        User user = createUserUsecase.handle(dto);
        return new UserResponse(user);
    }
}
