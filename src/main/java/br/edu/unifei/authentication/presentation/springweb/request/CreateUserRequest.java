package br.edu.unifei.authentication.presentation.springweb.request;

import br.edu.unifei.authentication.domain.entity.PermissionLevel;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CreateUserRequest {
    @NotEmpty
    @Length(max = 20)
    private String login;
    @NotNull
    @Min(0)
    @Max(2)
    private PermissionLevel permissionLevel;
}
