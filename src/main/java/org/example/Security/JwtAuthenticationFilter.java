package org.example.Security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtHelper jwtHelper;
    private final UserDetailsService userDetailsService;
    Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

//get token from Header
        String requestToken = request.getHeader("Authorization");
        logger.info("message", requestToken);
        String Username = null;
        String jwtToken = null;
        if (requestToken != null && requestToken.trim().startsWith("Bearer")) {
//      get actual token
            jwtToken = requestToken.substring(7);
            try {
                Username = this.jwtHelper.getUsername(jwtToken);
                logger.info("JWT Token parsed successfully. Username: {}", Username);

            } catch (ExpiredJwtException e) {
                logger.error("JWT token expired", e);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token expired");
                return;
            } catch (MalformedJwtException e) {
                logger.error("Invalid JWT token", e);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT token");
                return;
            } catch (IllegalArgumentException e) {
                logger.error("Unable to get JWT token", e);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unable to get JWT token");
                return;
            }
        }else {
            logger.warn("JWT token does not start with Bearer");
        }
            if (Username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                validate
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(Username);
                logger.info("UserDetails loaded for username: {}", Username);
                if (this.jwtHelper.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    logger.info("Authentication set in SecurityContext for username: {}", Username);
                } else {
                    logger.error("Invalid JWT token");
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
                    return;
                }
//                    logger.error("not validate Message  ", "Invalid Jwt token e");
//                }
//            } else {
//
//                logger.info("User Message", "username is null or auth is already there ");
//            }

//        } else {
//            logger.info("Token message { }", "Token does not start with bearer");
        }
        filterChain.doFilter(request, response);
    }

}
