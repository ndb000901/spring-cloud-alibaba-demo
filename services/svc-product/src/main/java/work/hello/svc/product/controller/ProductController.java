package work.hello.svc.product.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.hello.svc.model.product.Product;
import work.hello.svc.product.service.ProductService;

import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/product", method = RequestMethod.GET, params = { "id", "num"})
    public Product getProduct(Long id, Integer num, @RequestHeader("x-token") String token, HttpServletResponse response) throws InterruptedException {
//        Thread.sleep(3000);
        System.out.println("id: " + id + " num: " + num + " x-token: " + token);
        response.addHeader("x-tag", UUID.randomUUID().toString());
        return productService.getProduct(id, num);
    }
}
