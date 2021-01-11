package frentz.daniel.plants.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate result = new RestTemplate();
        return result;
    }
}
