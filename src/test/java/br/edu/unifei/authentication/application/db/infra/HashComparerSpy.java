package br.edu.unifei.authentication.application.db.infra;

import static org.mockito.Mockito.*;

public abstract class HashComparerSpy {
    public static HashComparer get() {
        HashComparer hashComparer = spy(HashComparer.class);

        when(hashComparer.compare(any(), any()))
                .thenReturn(true);

        return hashComparer;
    }
}
