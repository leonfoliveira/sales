package br.edu.unifei.authentication.presentation.springweb.request;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
public class SetPasswordUserRequest {
    @NotEmpty
    @Length(min = 8)
    private String password;
    private String placeholder;
}
