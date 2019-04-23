package com.backend.ws.hjemmesalgws.security;


import com.backend.ws.hjemmesalgws.SpringApplicationContext;
import com.backend.ws.hjemmesalgws.service.UserService;
import com.backend.ws.hjemmesalgws.shared.dao.User_DAO;
import com.backend.ws.hjemmesalgws.ui.usermodel.request.UserLoginRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Authentication_Filter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public Authentication_Filter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            UserLoginRequestModel credentials = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequestModel.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(),credentials.getPassword(), new ArrayList<>()));
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        String username = ((User) authentication.getPrincipal()).getUsername();
        String token = Jwts.builder().setSubject(username)
                            .setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
                            .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
                            .compact();

        UserService userService = (UserService)SpringApplicationContext.getBean("userService_Impl");//Her
        User_DAO user_dao = userService.getUser(username);

        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        response.addHeader("UserID", user_dao.getUserId());

    }
}
