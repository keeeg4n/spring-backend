package dev.keeg4n.spring_jwt.security.filter;

import dev.keeg4n.spring_jwt.service.JwtService;
import dev.keeg4n.spring_jwt.service.UserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailService userDetailService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserDetailService userDetailService
    ) {
        this.jwtService = jwtService;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        // Get the Auth Header
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Check if the auth header is null or doesn't start with Bearer
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            // Next filter
            filterChain.doFilter(request, response);
        }

        assert authHeader != null;
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        if (!Objects.isNull(userEmail) &&
                Objects.isNull(SecurityContextHolder.getContext().getAuthentication())
        ) {
            UserDetails userDetails = this.userDetailService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
