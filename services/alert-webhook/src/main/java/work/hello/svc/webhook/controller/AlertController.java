package work.hello.svc.webhook.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AlertController {

    @RequestMapping("/api/alert")
    public Map<String, String> alert(@RequestBody Map<Object, Object> body) {
        System.out.println(body);
        Map<String, String> result = new HashMap<>();
        result.put("code", "200");
        result.put("message", "success");

        
        return result;
    }
}
