package br.edu.unifei.authentication.presentation.springweb.request;

import br.edu.unifei.authentication.domain.entity.PermissionLevel;
import br.edu.unifei.common.validator.IsEnum;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
public class UpdateUserRequest {
    @NotEmpty
    @Length(max = 20)
    private String login;
    @IsEnum(enumClass = PermissionLevel.class)
    private String permissionLevel;
}
