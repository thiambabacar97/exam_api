package com.mourchidtech.examjeedevops.security.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public class JwtEnvironment {

    public String secret;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
