package br.edu.unifei.authentication.application.usecase.db.infra;

public interface TokenGenerator {
    String generate(Object payload);
}
