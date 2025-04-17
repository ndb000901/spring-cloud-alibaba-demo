package work.hello.svc.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import work.hello.svc.model.order.Order;
import work.hello.svc.order.component.SentinelDemo;
import work.hello.svc.order.dto.CreateOrderParam;
import work.hello.svc.order.service.OrderService;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private SentinelDemo sentinelDemo;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public Order createOrder(@RequestBody CreateOrderParam body) {
        sentinelDemo.sentinelResourceDemo();
        return orderService.createOrder(body.getUserId(), body.getProductId(), body.getNum());
    }
}
