package br.edu.unifei.authentication.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class User {
    private UUID id;
    private String login;
    private String password;
    private PermissionLevel permissionLevel;
    private Boolean isActive;
}
