package br.edu.unifei.authentication.application.db.repository;

import static org.mockito.Mockito.spy;

public class UpdateUserRepositorySpy {
    public static UpdateUserRepository get() {
        return spy(UpdateUserRepository.class);
    }
}
