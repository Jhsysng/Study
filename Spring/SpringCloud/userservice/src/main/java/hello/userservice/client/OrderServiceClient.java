package hello.userservice.client;

import hello.userservice.domain.dto.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("order-service")
public interface OrderServiceClient {
    @GetMapping("/order-service/{userId}/orders")
    List<ResponseOrder> getOrders(String userId);
}
