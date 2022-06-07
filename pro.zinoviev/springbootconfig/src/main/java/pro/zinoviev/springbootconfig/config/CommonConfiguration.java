package pro.zinoviev.springbootconfig.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.zinoviev.springbootconfig.entity.Droid;

@Configuration
public class CommonConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "droid")
    public Droid createDroid(){
        return new Droid();
    }
}
