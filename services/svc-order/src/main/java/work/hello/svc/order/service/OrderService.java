package work.hello.svc.order.service;

import work.hello.svc.model.order.Order;

public interface OrderService {

    Order createOrder(Long userId, Long productId, Integer num);
}
