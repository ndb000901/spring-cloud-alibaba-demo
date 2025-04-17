package work.hello.svc.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import work.hello.svc.model.product.Product;
import work.hello.svc.order.feign.fallback.ProductFallback;
import work.hello.svc.order.feign.fallback.ProductFallbackFactory;

//@FeignClient(value = "svc-product", fallback = ProductFallback.class)
@FeignClient(value = "svc-product", fallbackFactory = ProductFallbackFactory.class)
public interface ProductClient {

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    Product getProduct(@RequestParam("id") Long id, @RequestParam("num") Integer num);
}
