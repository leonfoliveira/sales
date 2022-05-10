package br.edu.unifei.authentication.application.db.infra;

public interface HashComparer {
    Boolean compare(String value, String hash);
}
