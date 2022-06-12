package br.edu.unifei.authentication.presentation.springweb.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
public class SetPasswordUserRequest {
    @NotEmpty
    private String password;
}
