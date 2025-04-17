package work.hello.svc.order.feign.fallback;

import org.springframework.stereotype.Component;
import work.hello.svc.model.product.Product;
import work.hello.svc.order.feign.ProductClient;

import java.math.BigDecimal;

@Component
public class ProductFallback implements ProductClient {

    @Override
    public Product getProduct(Long id, Integer num) {
        Product product = new Product();
        product.setId(0L);
        product.setPrice(new BigDecimal(0));
        product.setProductName("other");
        product.setNum(0);
        return product;
    }
}
