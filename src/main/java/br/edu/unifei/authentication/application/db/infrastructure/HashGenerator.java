package br.edu.unifei.authentication.application.db.infrastructure;

public interface HashGenerator {
    String generate(String value);
}
