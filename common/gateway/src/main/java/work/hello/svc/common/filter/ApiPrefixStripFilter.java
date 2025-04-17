package work.hello.svc.common.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ApiPrefixStripFilter implements GlobalFilter, Ordered {

    private static final String PREFIX = "/api";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getRawPath();
        System.out.println("ApiPrefixStripFilter run .....: " + path);
        if (path.startsWith(PREFIX)) {
            String newPath = path.substring(PREFIX.length());
            ServerHttpRequest newRequest = request.mutate()
                .path(newPath)
                .build();
            ServerWebExchange newExchange = exchange.mutate()
                .request(newRequest)
                .build();
            return chain.filter(newExchange);
        }

        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        // 确保在路由匹配前执行
        return -1;
    }
}
