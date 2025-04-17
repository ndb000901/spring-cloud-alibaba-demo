package work.hello.svc.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.hello.svc.model.order.OrderConfig;
import work.hello.svc.order.config.OrderPropertirs;

import java.util.HashMap;
import java.util.Map;

@RefreshScope
@RestController
public class ConfigController {


    @Value("${order.config.name}")
    private String name;

    @Value("${order.config.maxNum}")
    private int maxNum;

    @Autowired
    private OrderPropertirs orderPropertirs;

    @GetMapping("/order/config")
    public Map<String, Object> getConfig() {
        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("maxNum", String.valueOf(maxNum));
        result.put("config", orderPropertirs);
        return result;
    }
}
