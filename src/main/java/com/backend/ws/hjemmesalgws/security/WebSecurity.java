package com.backend.ws.hjemmesalgws.security;

import com.backend.ws.hjemmesalgws.service.UserService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final UserService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Method is used to set some entries to the webservice public, others as protected.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
                .permitAll().anyRequest().authenticated()
                .and().addFilter(getAuthenticationFilter())
                .addFilter(new Authorization_Filter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    /**
     * Metode tillader at ændre URL "placering" af login
     * @return filter, som ændrer url for login request.
     * @throws Exception
     */
    public Authentication_Filter getAuthenticationFilter() throws Exception {
        final Authentication_Filter filter = new Authentication_Filter(authenticationManager());
        filter.setFilterProcessesUrl("/users/login");
        return filter;
    }
}
