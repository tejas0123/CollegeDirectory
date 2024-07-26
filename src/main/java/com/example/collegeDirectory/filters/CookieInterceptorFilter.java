package com.example.collegeDirectory.filters;

import com.lms.model.MutableHttpServletRequest;
import com.lms.utils.UtilityMethods;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CookieInterceptorFilter extends OncePerRequestFilter {

    Logger log = LoggerFactory.getLogger(CookieInterceptorFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getRequestURI().startsWith("/api/v1/auth/") && request.getMethod().equalsIgnoreCase("POST")){
            doFilter(request, response, filterChain);
        }
        if(request.getCookies() == null){
            log.error("No cookies found");
            System.out.println("Error: No cookies found");
            UtilityMethods.sendForbiddenResponse(response);
        } else{
            List<Cookie>cookies = List.of(request.getCookies());
            Optional<Cookie> jwtCookieOptional = cookies
                    .stream()
                    .filter(cookie -> cookie.getName().equals("leaveMgmtSystem"))
                    .findFirst();
            if(jwtCookieOptional.isEmpty()){
                log.error("Authentication cookie not found");
                UtilityMethods.sendForbiddenResponse(response);
            } else{
                log.info("Cookie found, extracting JWT");
                var jwtToken = jwtCookieOptional.get().getValue();
                MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(request);
                mutableRequest.addHeader("Authorization", String.format("Bearer %s", jwtToken));
                log.info("Authorization header set");
                doFilter(mutableRequest, response, filterChain);
            }
        }
    }
}
