package work.hello.svc.model.order;

import lombok.Data;
import work.hello.svc.model.product.Product;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Order {

    private Long id;

    private BigDecimal totalAmount;

    private String userId;

    private String nickName;

    private String address;

    private List<Product> productList;
}
