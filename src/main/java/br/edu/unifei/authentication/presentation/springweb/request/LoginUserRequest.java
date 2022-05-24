package br.edu.unifei.authentication.presentation.springweb.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
public class LoginUserRequest {
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
}
