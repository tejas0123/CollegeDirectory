package com.example.collegeDirectory.filters;

import com.lms.config.JwtService;
import com.lms.model.modelUtils.APIResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    public void handleException(HttpServletResponse response, String message, int statusCode) throws IOException {
        if (!response.isCommitted()) {
            response.setStatus(statusCode);
            response.setContentType("application/json");
            response.getWriter().write(new APIResponse(false, message, null, HttpStatus.UNAUTHORIZED).toString());
            response.getWriter().close();
        }
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            log.error("Authorization Header not found");
            filterChain.doFilter(request, response);
            return;
        }

        log.info("Found Authorization header");
        jwt = authorizationHeader.substring(7);
        try {
            username = jwtService.extractUsername(jwt);
            log.debug("Extracted username in JwtFilter: " + username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                log.debug("Printing username from userDetails " + userDetails.getUsername());

                if (jwtService.isTokenValid(jwt, userDetails)) {
                    log.info("Token validated");

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    log.info("Auth token set in SecurityContextHolder");
                }
            } else {
                log.info("Already authenticated. Current User: " + username);
            }

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            log.error("Exception in JwtAuthenticationFilter: " + exception.getMessage());
            handleException(response, exception.getMessage(), HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
