package com.realm.jwt_security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secret;

    private long accessExpiry = 3600;

    private long refreshExpiry = 86400;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getAccessExpiry() {
        return accessExpiry;
    }

    public void setAccessExpiry(long expiry) {
        this.accessExpiry = expiry;
    }


    public long getRefreshExpiry() {
        return refreshExpiry;
    }

    public void setRefreshExpiry(long refreshExpiry) {
        this.refreshExpiry = refreshExpiry;
    }
}