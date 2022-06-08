package br.edu.unifei.authentication.infrastructure.jwt;

import br.edu.unifei.authentication.application.db.infrastructure.TokenGenerator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.Map;

@RequiredArgsConstructor
public class JwtAdapter implements TokenGenerator {
    private final String jwtSecret;
    private final Integer jwtExpirationTime;

    @Override
    public String generate(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationTime))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
