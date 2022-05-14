package br.edu.unifei.authentication.application.db.repository;

import static org.mockito.Mockito.spy;

public abstract class UpdateUserRepositorySpy {
    public static UpdateUserRepository get() {
        return spy(UpdateUserRepository.class);
    }
}
