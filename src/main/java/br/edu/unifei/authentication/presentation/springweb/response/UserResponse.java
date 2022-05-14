package br.edu.unifei.authentication.presentation.springweb.response;

import br.edu.unifei.authentication.domain.entity.PermissionLevel;
import br.edu.unifei.authentication.domain.entity.User;

import java.util.UUID;

public record UserResponse(UUID id, String login, PermissionLevel permissionLevel, Boolean isActive) {
    public UserResponse(User user) {
        this(user.getId(), user.getLogin(), user.getPermissionLevel(), user.getIsActive());
    }
}
