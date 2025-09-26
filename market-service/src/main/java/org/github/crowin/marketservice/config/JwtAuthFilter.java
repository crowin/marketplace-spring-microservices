package org.github.crowin.marketservice.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.github.crowin.marketservice.utils.JwtUtil;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component @Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    private final RestTemplate restTemplate;

    public JwtAuthFilter(Environment env, RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .rootUri(env.getProperty("internal-resources.users-service-url"))
                .build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                var resp = restTemplate.exchange(
                        "/validate-token",
                        HttpMethod.GET,
                        new HttpEntity<>(null, createHeaders(authHeader)), Object.class
                );

                if (resp.getStatusCode().is2xxSuccessful()) {
                    log.info("User passed token verification: {}", resp.getStatusCode());
                    var token = authHeader.substring(7);
                    var userId = JwtUtil.extractUserId(token);

                    var authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
                    var auth = new UsernamePasswordAuthenticationToken(userId, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    filterChain.doFilter(request, response);
                    return;
                } else log.error("User couldn't pass token verification");

            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }
        }
        log.error("Missing or invalid Authorization header");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
    }

    private HttpHeaders createHeaders(String authHeader) {
        var headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        return headers;
    }
}
