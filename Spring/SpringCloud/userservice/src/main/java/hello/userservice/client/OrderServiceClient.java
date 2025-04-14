package hello.userservice.client;

import hello.userservice.domain.dto.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("order-service")
public interface OrderServiceClient {
    @GetMapping("/order-service/orders/{userId}")
    List<ResponseOrder> getOrders(@PathVariable String userId);
}
