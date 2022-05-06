package br.edu.unifei.authentication.application.dto;

import br.edu.unifei.authentication.domain.entity.PermissionLevel;

public record CreateUserDTO(String login, String password, PermissionLevel permissionLevel) {
}
