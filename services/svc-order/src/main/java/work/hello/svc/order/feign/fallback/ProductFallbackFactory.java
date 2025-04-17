package work.hello.svc.order.feign.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import work.hello.svc.model.product.Product;
import work.hello.svc.order.feign.ProductClient;

import java.math.BigDecimal;

@Slf4j
@Component
public class ProductFallbackFactory implements FallbackFactory<ProductClient> {

    @Override
    public ProductClient create(Throwable cause) {
        log.error("fallback: ", cause);
        return (id, num) -> {
            Product product = new Product();
            product.setId(0L);
            product.setPrice(new BigDecimal(0));
            product.setProductName("other");
            product.setNum(0);
            return product;
        };

    }
}
