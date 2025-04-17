package work.hello.svc.order.feign.interceptor;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.UUID;

@Component
public class TokenInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.removeHeader("x-token");
        template.header("x-token", UUID.randomUUID().toString());
    }
}
