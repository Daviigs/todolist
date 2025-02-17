package com.davi.dev.todolist.filter;

import com.davi.dev.todolist.repository.IUserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.startsWith("/tasks/")) {

            var authorization = request.getHeader("Authorization");
            authorization = authorization.substring("Basic".length()).trim();

            byte[] authDecode = Base64.getDecoder().decode(authorization);
            var authString = new String(authDecode);

            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            var user = userRepository.findByUsername(username);
            if (user == null) {
                response.sendError(401);
            } else {

                boolean passwordVerify = (password.equals(user.getPassword()));
                if (passwordVerify) {
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
            }

        } else {
            filterChain.doFilter(request, response);
        }
    }
}
