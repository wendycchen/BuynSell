package com.cgi.filters;

import com.cgi.exceptions.JwtTokenInvalidException;
import com.cgi.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;
@RestController
@Component @Slf4j @RequiredArgsConstructor
public class JwtFilter implements GatewayFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        log.info("---------------------------------------------------------");
        log.info("{}",request.getURI());
        log.info("---------------------------------------------------------");
        log.info("---------------------------------------------------------");

        //Open endpoints that don't require our user to authenticate
        final List<String> apiEndpoints = List.of("/api/v1/account/register", "/api/v1/account/login","/api/v1/account/confirm/*");

        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints
                .stream()
                .noneMatch(uri -> r.getURI().getPath().contains(uri));

        //Using a predicate to determine if the endpoint is allowed to be accessed w/o token
        if (isApiSecured.test(request)) {
            //Checking to see if the http request contains Authorization in its header
            if (!request.getHeaders().containsKey("Authorization")) {
                //Sending UNAuthorized if no authorization key was given in header
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            final String token = request.getHeaders().getOrEmpty("Authorization").get(0);


            //Using Jwtutil to check if the token is valid by comparing secrets
            try {
                jwtUtil.validateToken(token);
            } catch (JwtTokenInvalidException e) {
                // If the token had wrong signature, was tampered with,expired, or was empty
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.BAD_REQUEST);
                log.info("{}",e.getMessage());
                return response.setComplete();
            }
            //Front end authenticating to go to different "page"
            Claims claims = jwtUtil.getClaims(token);
            if(request.getURI().getPath().equals("/authenticate")){
                ServerHttpResponse response = exchange.getResponse();
                response.getHeaders().add("email",claims.getSubject());
                response.getHeaders().add("role",claims.get("role").toString());
                response.setStatusCode(HttpStatus.ACCEPTED);
                return response.setComplete();
            }

            exchange.getRequest().mutate().header("email",claims.getSubject()).header("role",String.valueOf(claims.get("role"))).build();
            log.info("Headers added, email: {}, role: {}",claims.getSubject(),claims.get("role"));
        }
        return chain.filter(exchange);
    }

}
