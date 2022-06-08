package br.edu.unifei.authentication.application.db.infrastructure;

public interface HashComparer {
    Boolean compare(String value, String hash);
}
