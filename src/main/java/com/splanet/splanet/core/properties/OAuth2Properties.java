package com.splanet.splanet.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.security.oauth2")
public class OAuth2Properties {
<<<<<<< HEAD
    private String redirectUrl;
=======
    private String redirectProdUrl;
    private String redirectDevUrl;
    private String originDev;
    private String originProd;
>>>>>>> weekly/11
}
