package br.edu.unifei.authentication.application.db.repository;

import static org.mockito.Mockito.spy;

public abstract class InsertUserRepositorySpy {
    public static InsertUserRepository get() {
        return spy(InsertUserRepository.class);
    }
}
