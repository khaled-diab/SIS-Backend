package com.sis.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtTokenFilter extends GenericFilterBean {
    private static final String BEARER = "Bearer";

    private final SecurityDetailsService securityDetailsService;

    public JwtTokenFilter(SecurityDetailsService securityDetailsService) {
        this.securityDetailsService = securityDetailsService;
    }

    /**
     * Determine if there is a JWT as part of the HTTP Request Header.
     * If it is valid then set the current context With the Authentication(user and roles) found in the token
     *
     * @param req         Servlet Request
     * @param res         Servlet Response
     * @param filterChain Filter Chain
     * @throws IOException
     * @throws ServletException
     */
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
//            throws IOException, ServletException {
//        LOGGER.info("Process request to check for a JSON Web Token ");
//        //Check for Authorization:Bearer JWT
//        String headerValue = ((HttpServletRequest) req).getHeader("Authorization");
//        getBearerToken(headerValue).ifPresent(token -> {
//            //Pull the Username and Roles from the JWT to construct the user details
//            applicationDetailsService.loadUserByJwtToken(token).ifPresent(userDetails -> {
//                //Add the user details (Permissions) to the Context for just this API invocation
//                SecurityContextHolder.getContext().setAuthentication(
//                        new PreAuthenticatedAuthenticationToken(userDetails, "", userDetails.getAuthorities()));
//            });
//        });
//
//        //move on to the next filter in the chains
//        filterChain.doFilter(req, res);
//    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        logger.info("Process request to check for a JSON Web Token ");
        //Check for Authorization:Bearer JWT
        String headerValue = ((HttpServletRequest) req).getHeader("jwt-token");
        //Pull the Username and Roles from the JWT to construct the user details
        getBearerToken(headerValue).flatMap(securityDetailsService::loadUserByJwtToken).ifPresent(userDetails -> {
            //Add the user details (Permissions) to the Context for just this API invocation
            SecurityContextHolder.getContext().setAuthentication(
                    new PreAuthenticatedAuthenticationToken(userDetails, "", userDetails.getAuthorities()));
        });

        //move on to the next filter in the chains
        filterChain.doFilter(req, res);
    }

    /**
     * if present, extract the jwt token from the "Bearer <jwt>" header value.
     *
     * @param headerVal
     * @return jwt if present, empty otherwise
     */
    private Optional<String> getBearerToken(String headerVal) {
        if (headerVal != null && headerVal.startsWith(BEARER)) {
            return Optional.of(headerVal.replace(BEARER, "").trim());
        }
        return Optional.empty();
    }
}
