package br.edu.unifei.authentication.application.model;

import br.edu.unifei.authentication.domain.entity.PermissionLevel;

import java.util.UUID;

public record AuthorizationPayload(UUID userId, String login, PermissionLevel permissionLevel) {
}
