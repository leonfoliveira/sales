package br.edu.unifei.authentication.presentation.springweb.response;

import java.util.UUID;

public record ResetPasswordUserResponse(String setPath) {
    public ResetPasswordUserResponse(UUID userId) {
        this("/users/" + userId.toString() + "/password/set");
    }
}
