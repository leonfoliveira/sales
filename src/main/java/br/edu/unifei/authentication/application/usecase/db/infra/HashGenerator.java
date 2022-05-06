package br.edu.unifei.authentication.application.usecase.db.infra;

public interface HashGenerator {
    String generate(String value);
}
