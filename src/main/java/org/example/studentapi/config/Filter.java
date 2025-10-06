package org.example.studentapi.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.studentapi.entity.Account;
import org.example.studentapi.exception.exceptions.AuthenticationException;
import org.example.studentapi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.List;

@Component
public class Filter extends OncePerRequestFilter {

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Autowired
    TokenService tokenService;

    private final List<String> PUBLIC_API = List.of(
            "GET:/swagger-ui/**",
            "GET:/v3/api-docs/**",
            "GET:/swagger-resources/**",
            "POST:/api/register",
            "POST:/api/login"
    );

    public boolean isPublicAPI(String uri, String method) {
        AntPathMatcher matcher = new AntPathMatcher();

//        if(method.equals("GET")) return true;

        return PUBLIC_API.stream().anyMatch(pattern -> {
            String[] parts = pattern.split(":", 2);
            if (parts.length != 2) return false;

            String allowedMethod = parts[0];
            String allowedUri = parts[1];

            return matcher.match(allowedUri, uri);
        });
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Filter running...");
        String uri = request.getRequestURI();
        String method = request.getMethod();
        if(isPublicAPI(uri, method)){
            //API publlic
            // => access
            filterChain.doFilter(request, response);
        }else{
            //API theo role
            // => check xem có quyền không
            // => check token
            String token = getToken(request);
            if(token == null){
                resolver.resolveException(request, response, null, new AuthenticationException("Token cannot empty!"));
            }
            // có token
            // verify lại token
            Account account = null;
            try {
                account = tokenService.extractToken(token);
            }catch(ExpiredJwtException expiredJwtException){
                //1. token hết hạn
                resolver.resolveException(request, response, null, new AuthenticationException("Token is expired!"));
            }catch(MalformedJwtException malformedJwtException){
                //2. token sai
                resolver.resolveException(request, response, null, new AuthenticationException("Invalid token!"));
            }
            //lưu thông tin thằng đang request
            UsernamePasswordAuthenticationToken
                    authenToken =
                    new UsernamePasswordAuthenticationToken(account, token, account.getAuthorities());
            authenToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenToken);


            // token chuẩn
            // được truy cập vào hệ thống
            filterChain.doFilter(request, response);




        }
    }

    public String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.substring(7);
    }
}
