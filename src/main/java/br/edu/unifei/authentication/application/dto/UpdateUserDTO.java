package br.edu.unifei.authentication.application.dto;

import br.edu.unifei.authentication.domain.entity.PermissionLevel;

import java.util.UUID;

public record UpdateUserDTO(UUID id, String login, PermissionLevel permissionLevel) {
}
