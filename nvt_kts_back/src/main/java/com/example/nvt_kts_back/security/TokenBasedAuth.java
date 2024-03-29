package com.example.nvt_kts_back.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class TokenBasedAuth extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 1L;

    private String token;
    private final UserDetails principle;

    public TokenBasedAuth(UserDetails principle) {
        super(principle.getAuthorities());
        this.principle = principle;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public UserDetails getPrincipal() {
        return principle;
    }

}
