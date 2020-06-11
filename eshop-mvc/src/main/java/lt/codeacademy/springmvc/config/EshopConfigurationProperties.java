package lt.codeacademy.springmvc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("eshop")
public class EshopConfigurationProperties {
    private String countryOfOperation;
    private String pvmRate;
}
