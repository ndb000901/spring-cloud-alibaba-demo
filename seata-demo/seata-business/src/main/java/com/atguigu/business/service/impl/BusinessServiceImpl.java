package com.atguigu.business.service.impl;

import com.atguigu.business.feign.OrderClient;
import com.atguigu.business.feign.StorageClient;
import com.atguigu.business.service.BusinessService;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private StorageClient storageClient;

    @GlobalTransactional
    @Override
    public void purchase(String userId, String commodityCode, int orderCount) {
        //TODO 1. 扣减库存
        storageClient.deduct(commodityCode, orderCount);

        //TODO 2. 创建订单
        orderClient.create(userId, commodityCode, orderCount);
    }
}
