package work.hello.svc.common.predicate;

import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Component
public class MyUserRoutePredicateFactory extends AbstractRoutePredicateFactory<MyUserRoutePredicateFactory.Config> {

    public MyUserRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        System.out.println("MyUserRoutePredicateFactory...");
        return exchange -> {
            System.out.println("MyUserRoutePredicateFactory...111");
            String username = exchange.getRequest().getQueryParams().getFirst("user");
            return username != null && username.equals(config.username);
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("username");
    }

    @Data
    public static class Config {
        private String username;
    }
}
