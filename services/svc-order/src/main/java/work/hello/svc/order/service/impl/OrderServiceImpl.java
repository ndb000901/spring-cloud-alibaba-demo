package work.hello.svc.order.service.impl;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import work.hello.svc.model.order.Order;
import work.hello.svc.model.product.Product;
import work.hello.svc.order.feign.ProductClient;
import work.hello.svc.order.service.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private ProductClient productClient;

    @Override
    public Order createOrder(Long userId, Long productId, Integer num) {

//        String service = getService();
//        String service = getServiceByLoadBalancer();
        // 使用服务名
//        String service = "http://svc-product";
//        System.out.println(service);
//        Product product = restTemplate.getForObject(service + "/product?id=" + productId + "&num=" + num, Product.class);
        Product product = productClient.getProduct(productId, num);
        if (product == null) {
            return null;
        }
        Order order = new Order();
        Long now = System.currentTimeMillis();
        order.setId(now);
        order.setAddress("address-" + now);
        order.setUserId(userId.toString());
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setNickName("nickName-" + now);
        List<Product> list = new ArrayList<>();
        list.add(product);
        order.setProductList(list);
        return order;
    }

    public String getService() {
        List<ServiceInstance> instances = discoveryClient.getInstances("svc-product");
        if (!instances.isEmpty()) {
            return instances.get(0).getUri().toString();
        }
        return null;
    }

    public String getServiceByLoadBalancer() {
        ServiceInstance instance = loadBalancerClient.choose("svc-product");
        return instance.getUri().toString();
    }



}
