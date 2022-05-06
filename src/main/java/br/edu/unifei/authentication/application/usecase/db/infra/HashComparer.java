package br.edu.unifei.authentication.application.usecase.db.infra;

public interface HashComparer {
    Boolean compare(String value, String hash);
}
