package br.edu.unifei.authentication.persistence.jpa.entity;

import br.edu.unifei.authentication.domain.entity.PermissionLevel;
import br.edu.unifei.authentication.domain.entity.User;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class JpaUser {
    @Id
    private UUID id;
    private String login;
    private String password;
    private PermissionLevel permissionLevel;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public JpaUser() {
    }

    public JpaUser(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.permissionLevel = user.getPermissionLevel();
        this.isActive = user.getIsActive();
    }

    public User toDomainEntity() {
        return User.builder()
                .id(this.id)
                .login(this.login)
                .password(this.password)
                .permissionLevel(this.permissionLevel)
                .isActive(this.isActive)
                .build();
    }
}
