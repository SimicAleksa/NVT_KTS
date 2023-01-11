package com.example.nvt_kts_back.configurations;

import com.example.nvt_kts_back.security.AuthEntryPoint;
import com.example.nvt_kts_back.security.TokenAuthFilter;
import com.example.nvt_kts_back.security.TokenUtils;
import com.example.nvt_kts_back.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SuppressWarnings("deprecation")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private TokenUtils tokenUtils;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
                .authorizeRequests()
                .antMatchers("/unauth/**", "/h2-console/**", "/h2-console", "/api/drivers/**","/api/rides/**","/socket/**","/map-updates/**").permitAll()
                .anyRequest().authenticated().and()
                .cors().and()
                .addFilterBefore(
                        new TokenAuthFilter(tokenUtils, customUserDetailsService),
                        BasicAuthenticationFilter.class
                );
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.GET, "/api/mail/password-reset/*");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/unauth/login", "/api/unauth/fb-login");
        web.ignoring().antMatchers(HttpMethod.PUT, "/api/user/password-reset");
    }
}
