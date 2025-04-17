package work.hello.svc.common.filter;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class MyLogGatewayFilterFactory extends AbstractGatewayFilterFactory<MyLogGatewayFilterFactory.Config> {
    public MyLogGatewayFilterFactory() {
        // 指定配置类
        super(Config.class);
    }

    // 过滤器的执行逻辑
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // 逻辑：打印请求日志
            ServerHttpRequest request = exchange.getRequest();
            System.out.println("[MyLogFilter] 请求路径: " + request.getPath());

            if (config.printParams) {
                request.getQueryParams().forEach((k, v) -> {
                    System.out.println("[MyLogFilter] 参数: " + k + "=" + v);
                });
            }

            // 继续过滤链
            return chain.filter(exchange);
        };
    }

    // 支持快捷配置字段顺序，例如 filters: - MyLog=true
    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("printParams");
    }

    // 配置类
    @Data
    public static class Config {
        private boolean printParams; // 是否打印参数
    }
}
