package work.hello.svc.product.service.impl;

import org.springframework.stereotype.Service;
import work.hello.svc.model.product.Product;
import work.hello.svc.product.service.ProductService;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getProduct(Long id, Integer num) {
        Product product = new Product();
        product.setId(id);
        product.setProductName("macbook-" + id);
        product.setPrice(new BigDecimal(100));
        product.setNum(num);
        return product;
    }
}
