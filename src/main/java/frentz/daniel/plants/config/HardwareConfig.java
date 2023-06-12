package frentz.daniel.plants.config;

import frentz.daniel.hardwareservice.client.service.HardwareClient;
import frentz.daniel.hardwareservice.client.service.HardwareClientImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@ConfigurationProperties("hardware.client")
public class HardwareConfig {

    private String host;
    private String port;
    private String scheme;

    @Bean
    HardwareClient hardwareClient(RestTemplate restTemplate) throws URISyntaxException {
        URI uri = new URI(scheme + "://" + host + ":" + port + "/");
        HardwareClient result = new HardwareClientImpl(uri, restTemplate);
        return result;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }
}
