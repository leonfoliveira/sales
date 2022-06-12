package br.edu.unifei.common.configuration;

import br.edu.unifei.authentication.application.model.AuthorizationPayload;
import br.edu.unifei.authentication.domain.entity.PermissionLevel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.UUID;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final String jwtSecret;
    private final SpringSecurityRoleMapper springSecurityRoleMapper = new SpringSecurityRoleMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authorization == null || !authorization.startsWith("Bearer ")) {
                throw new Exception();
            }

            final String accessToken = authorization.split(" ")[1].trim();
            Jws<Claims> claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(accessToken);
            LinkedHashMap<String, Object> plainPayload = (LinkedHashMap<String, Object>) claims.getBody().get("payload");
            AuthorizationPayload payload = new AuthorizationPayload(
                    UUID.fromString((String) plainPayload.get("userId")),
                    (String) plainPayload.get("login"),
                    PermissionLevel.valueOf((String) plainPayload.get("permissionLevel"))
            );

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(payload,
                            null,
                            springSecurityRoleMapper.map(payload.permissionLevel()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            filterChain.doFilter(request, response);
        }
    }
}
