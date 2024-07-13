package study.jpashop.repository.order.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import study.jpashop.domain.Address;
import study.jpashop.domain.OrderStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderFlatDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    private String itemName;
    private int orderPrice;
    private int count;


}
