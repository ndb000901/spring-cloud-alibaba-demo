package com.atguigu.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("seata-account")
public interface AccountClient {

    @GetMapping("/debit")
    String debit(@RequestParam("userId") String userId,
                        @RequestParam("money") int money);
}
