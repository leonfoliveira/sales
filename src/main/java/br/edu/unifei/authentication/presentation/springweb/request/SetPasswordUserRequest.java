package br.edu.unifei.authentication.presentation.springweb.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class SetPasswordUserRequest {
    @NotEmpty
    @Min(8)
    @Max(200)
    private String password;
}
