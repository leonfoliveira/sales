package br.edu.unifei.authentication.infrastructure.bcrypt;

import br.edu.unifei.authentication.application.db.infrastructure.HashComparer;
import br.edu.unifei.authentication.application.db.infrastructure.HashGenerator;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptAdapter implements HashComparer, HashGenerator {
    @Override
    public Boolean compare(String value, String hash) {
        return BCrypt.checkpw(value, hash);
    }

    @Override
    public String generate(String value) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(value, salt);
    }
}
