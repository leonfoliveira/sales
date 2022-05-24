package br.edu.unifei.authentication.presentation.springweb.controller;

import br.edu.unifei.authentication.application.contract.LoginUserUsecase;
import br.edu.unifei.authentication.application.model.Authorization;
import br.edu.unifei.authentication.presentation.springweb.request.LoginUserRequest;
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
public class LoginUserController {
    private final LoginUserUsecase loginUserUsecase;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get authorization token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Wrong password or user not found with provided login")
    })
    public Authorization handle(@RequestBody @Valid LoginUserRequest body) {
        return loginUserUsecase.handle(body.getLogin(), body.getPassword());
    }
}
