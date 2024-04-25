package com.hq.treasurechest.filters;

import com.hq.treasurechest.utils.JWTUtil;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

// OncePerRequestFilter:确保每个请求只被过滤一次
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {
        // check Authorization Header
        final String authorizationHeader = request.getHeader("Authorization");

        String jwt = null;
        String username = null;

        // Bearer is used as an identifier for the JWT authentication scheme
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            jwt = authorizationHeader.substring(7); // get jwt token
            username = JWTUtil.extractUsername(jwt); // get username
        }

        // token验证通过则将存入security上下文
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // 校验从前端传过来的token信息
            if (JWTUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else {
                throw new AccessDeniedException("权限不足");
            }
        }
        filterChain.doFilter(request, response);
    }
}
