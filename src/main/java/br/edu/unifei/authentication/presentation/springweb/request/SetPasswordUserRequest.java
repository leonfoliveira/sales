package br.edu.unifei.authentication.presentation.springweb.request;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class SetPasswordUserRequest {
    @NotEmpty
    @Length(min = 8, max = 200)
    private String password;
}
