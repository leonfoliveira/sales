package br.edu.unifei.common.configuration;

import br.edu.unifei.authentication.domain.entity.PermissionLevel;

import java.util.List;

public class SpringSecurityRoleMapper {
    private final List<SpringSecurityRole> securityRoles = List.of(
            new SpringSecurityRole("ROLE_COLLABORATOR"),
            new SpringSecurityRole("ROLE_MANAGER"),
            new SpringSecurityRole("ROLE_ADMIN")
    );

    public List<SpringSecurityRole> map(PermissionLevel permissionLevel) {
        return securityRoles.subList(0, permissionLevel.ordinal() + 1);
    }
}
