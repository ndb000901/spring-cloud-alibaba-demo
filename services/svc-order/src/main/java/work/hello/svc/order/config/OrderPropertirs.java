package work.hello.svc.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "order.config")
@Data
public class OrderPropertirs {

    private String name;

    private int maxNum;
}
