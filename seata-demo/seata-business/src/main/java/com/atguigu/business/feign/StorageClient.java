package com.atguigu.business.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("seata-storage")
public interface StorageClient {

    @GetMapping("/deduct")
    String deduct(@RequestParam("commodityCode") String commodityCode,
                         @RequestParam("count") Integer count);
}
