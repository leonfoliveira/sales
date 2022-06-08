package br.edu.unifei.authentication.application.db.infrastructure;

import java.util.Map;

public interface TokenGenerator {
    String generate(Map<String, Object> claims);
}
