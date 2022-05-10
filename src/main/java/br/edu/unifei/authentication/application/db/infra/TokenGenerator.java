package br.edu.unifei.authentication.application.db.infra;

public interface TokenGenerator {
    String generate(Object payload);
}
