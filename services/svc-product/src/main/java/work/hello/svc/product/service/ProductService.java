package work.hello.svc.product.service;

import work.hello.svc.model.product.Product;

public interface ProductService {

    Product getProduct(Long id, Integer num);
}
