package work.hello.svc.common.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//            .route("order_route", r -> r.path("/api/order/**")
//                .filters(f -> f.stripPrefix(1))
//                .uri("lb://order-service"))
//            .build();
//    }
}
