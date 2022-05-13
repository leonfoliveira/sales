package br.edu.unifei.authentication.application.model;

import br.edu.unifei.authentication.domain.entity.User;

public record Authorization(String accessToken, User user) {
}
