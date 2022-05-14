package br.edu.unifei.authentication.presentation.springweb.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class LoginUserRequest {
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
}
