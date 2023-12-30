package com.nagarro.training.NoteSync.config;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
/**
 * This class handles the entry point for JWT authentication.
 * It implements the AuthenticationEntryPoint interface, which allows custom handling
 * of unauthenticated requests.
 * @author shreyarathour
 */
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    /**
     * This method is called when a user tries to access a protected resource without proper authentication.
     * It sends an HTTP error response with the status code 401 (Unauthorized) and a message indicating that
     * the request is unauthorized.
     *
     * @param request       The HTTP request.
     * @param response      The HTTP response.
     * @param authException The exception representing the authentication failure.
     * @throws IOException      If an input or output exception occurs while handling the request.
     * @throws ServletException If a servlet-specific exception occurs while handling the request.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        // TODO Auto-generated method stub
        // This method is called when a user tries to access a protected resource without proper authentication

        // Send an HTTP error response with the status code 401 (Unauthorized)
        // and a message indicating that the request is unauthorized
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");

    }


}
