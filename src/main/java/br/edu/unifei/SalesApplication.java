package br.edu.unifei;

import br.edu.unifei.authentication.domain.entity.PermissionLevel;
import br.edu.unifei.authentication.persistence.jpa.entity.JpaUser;
import br.edu.unifei.authentication.persistence.jpa.repository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class SalesApplication implements ApplicationRunner {
    @Autowired
    private JpaUserRepository jpaUserRepository;
    @Value("${rootPassword}")
    private String rootPassword;

    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        Optional<JpaUser> root = jpaUserRepository.findByLogin("root");
        if (root.isEmpty()) {
            JpaUser jpaUser = new JpaUser();
            jpaUser.setId(UUID.randomUUID());
            jpaUser.setLogin("root");
            jpaUser.setPassword(BCrypt.hashpw(rootPassword, BCrypt.gensalt()));
            jpaUser.setPermissionLevel(PermissionLevel.ADMIN);
            jpaUser.setCreatedAt(LocalDateTime.now());
            jpaUser.setIsActive(true);
            jpaUserRepository.save(jpaUser);
        }
    }
}
