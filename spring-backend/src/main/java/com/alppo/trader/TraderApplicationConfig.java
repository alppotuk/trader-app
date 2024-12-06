package com.alppo.trader;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TraderApplicationConfig {
    @Value("${spring.profile.active:default}")
    private String activeProfile;

    @PostConstruct
    public void logActiveProfile(){
        System.out.println("Active profile: " + activeProfile);
    }
}
