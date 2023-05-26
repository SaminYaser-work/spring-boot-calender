package com.example.demo.configs;

import com.example.demo.model.CustomUserDetails;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.util.JWTUtility;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    // https://github.com/dailycodebuffer/Spring-MVC-Tutorials/blob/master/JWT-Demo/pom.xml

    private final JWTUtility jwtUtility;

    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException
    {
        final String authHeader = request.getHeader("Authorization");
        final String refreshToken = request.getHeader("Refresh-Token");

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("No Bearer token found");
            filterChain.doFilter(request, response);
            return;
        }

        log.info("Bearer token found");

        String jwtToken = authHeader.substring(7);
        String username = null;

        try{
            username = jwtUtility.getUsernameFromToken(jwtToken);
        }
        catch (Exception ex){
            log.info("Token is expired");
            if (refreshToken != null) {
                username = jwtUtility.getUsernameFromToken(refreshToken);
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                jwtToken = jwtUtility.generateToken(userDetails);
                final String newRefreshToken = jwtUtility.generateRefreshToken(userDetails);
                response.setHeader("Refresh-Token", newRefreshToken);
            } else {
                log.warn("No refresh token found");
                filterChain.doFilter(request, response);
                return;
            }
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails
                    = customUserDetailsService.loadUserByUsername(username);

            if(jwtUtility.validateToken(jwtToken, userDetails)) {
                log.info("Username is valid");

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(
                                userDetails,
                            null,
                        userDetails.getAuthorities()
                );

                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(
                                        request
                                )
                );

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else if(jwtUtility.isTokenExpired(jwtToken)) {
                log.warn("Token is expired or username does not match");
            }
        }

        log.info(jwtToken);
        filterChain.doFilter(request, response);
    }
}
