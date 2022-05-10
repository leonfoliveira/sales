package br.edu.unifei.authentication.application.db.infra;

public interface HashGenerator {
    String generate(String value);
}
