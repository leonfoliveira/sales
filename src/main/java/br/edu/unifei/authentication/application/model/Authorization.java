package br.edu.unifei.authentication.application.model;

public record Authorization(String accessToken, AuthorizationPayload payload) {
}
