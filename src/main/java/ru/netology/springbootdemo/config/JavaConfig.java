package ru.netology.springbootdemo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.springbootdemo.implementation.DevProfile;
import ru.netology.springbootdemo.implementation.ProductionProfile;
import ru.netology.springbootdemo.implementation.SystemProfile;

@Configuration
public class JavaConfig {

    @ConditionalOnProperty(value = "netology.profile.dev", havingValue = "true")
    @Bean
    public SystemProfile devProfile(){
        return new DevProfile();
    }

    @ConditionalOnProperty(value = "netology.profile.dev", havingValue = "false")
    @Bean
    public SystemProfile prodProfile(){
        return new ProductionProfile();
    }
}
