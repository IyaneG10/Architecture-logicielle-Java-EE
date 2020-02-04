package appConfig;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="sysco")
@Data
public class SyscoConfiguration {
    Rabbitmq rabbitmq;

    @Data
    public static class Rabbitmq {
        String address;
        String port;
    }
}
