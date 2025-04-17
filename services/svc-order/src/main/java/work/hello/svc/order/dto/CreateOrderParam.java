package work.hello.svc.order.dto;

import lombok.Data;

@Data
public class CreateOrderParam {

    private Long userId;

    private Long productId;

    private Integer num;
}
